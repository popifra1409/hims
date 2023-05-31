package co.iaf.service.identification;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.identification.GroupePatientRepository;
import co.iaf.dao.identification.InfoSupRepository;
import co.iaf.dao.identification.PatientRepository;
import co.iaf.dao.identification.QrCodePatientRepository;
import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.identification.GroupePatient;
import co.iaf.entity.identification.InfosSup;
import co.iaf.entity.identification.Patient;
import co.iaf.entity.identification.QrCodePatient;
import co.iaf.utils.QrCodeGenerator;

@Service
@Transactional
public class IdentificationServiceImpl implements IdentificationService {

	// private static final String QR_CODE_IMAGE_PATH = "/img/";

	private PatientRepository patientRepo;
	private InfoSupRepository infoSupRepo;
	private QrCodePatientRepository qrCodePatientRepo;
	private GroupePatientRepository groupepatientRepo;

	public IdentificationServiceImpl(PatientRepository patientRepo, InfoSupRepository infoSupRepo,
			QrCodePatientRepository qrCodePatientRepo, GroupePatientRepository groupepatientRepo) {
		super();
		this.patientRepo = patientRepo;
		this.infoSupRepo = infoSupRepo;
		this.qrCodePatientRepo = qrCodePatientRepo;
		this.groupepatientRepo = groupepatientRepo;
	}

	@Override
	public Patient addNewPatient(Patient patient) {
		Collection<InfosSup> infosupp = patient.getInfosSup();

		Patient pat = this.patientRepo.save(patient);
		// on genere leQrCode du patient
		addNewQrCodePatient(new QrCodePatient(), pat.getPatientId());

		// informations supplémentaires
		if (infosupp != null) {
			infosupp.forEach(info -> {
				this.infoSupRepo.save(new InfosSup(null, info.getCle(), info.getValeur(), pat));
			});
		}
		return pat;
	}

	@Override
	public Patient updatePatient(String patientId, Patient patient) {
		Patient p = getPatientById(patientId);
		p.setPatientFirstName(patient.getPatientFirstName());
		p.setPatientLastName(patient.getPatientLastName());
		p.setPatientBirthDay(patient.getPatientBirthDay());
		p.setPatientPlaceOfBirth(patient.getPatientPlaceOfBirth());
		p.setPatientSex(patient.getPatientSex());
		p.setPatientAge(patient.getPatientAge());
		p.setPatientProfession(patient.getPatientProfession());
		p.setPatientReligion(patient.getPatientReligion());
		p.setPatientNationalite(patient.getPatientNationalite());
		p.setEmail(patient.getEmail());
		p.setTelephone(patient.getTelephone());
		p.setAdresse(patient.getAdresse());

		return this.patientRepo.save(p);
	}

	@Override
	public void deletePatient(Patient patient) {
		this.patientRepo.delete(patient);
	}

	@Override
	public Patient getPatientById(String patientId) {
		Patient patient = this.patientRepo.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "Patient Id", 0));
		return patient;
	}

	@Override
	public Collection<Patient> getPatientsByIds(List<String> patientIds) {
		return patientRepo.findAllById(patientIds);
	}

	@Override
	public List<Patient> getAllPatients() {
		return this.patientRepo.findAll();
	}

	@Override
	public List<Patient> getPatientsByLastNameOrFirstName(String lastname, String firstname) {
		return this.patientRepo.findByPatientLastNameContainingOrPatientFirstNameContaining(lastname, firstname);
	}

	@Override
	public void mergePatient(Patient patientPrincipal, Collection<Patient> patientsSecondaires) {
		// on recupère le patient principal
		if (patientsSecondaires != null && !patientsSecondaires.isEmpty()) {
			for (Patient patient : patientsSecondaires) {
				fusionnerDeuxPatients(patientPrincipal, patient);
			}
		}
	}

	@Override
	public InfosSup addNewInfoToPatient(InfosSup info, String patientId) {
		Patient patient = this.patientRepo.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient:", "Patient Id", 0));

		info.setPatient(patient);

		return this.infoSupRepo.save(info);
	}

	@Override
	public InfosSup updateInfosSup(Long infoId, InfosSup info) {
		if (getInfosSupById(infoId) != null) {
			info.setId(infoId);
			return this.infoSupRepo.save(info);
		}
		return null;
	}

	@Override
	public void deleteInfosSup(InfosSup info) {
		this.infoSupRepo.delete(info);
	}

	@Override
	public InfosSup getInfosSupById(Long infosSupId) {
		InfosSup info = this.infoSupRepo.findById(infosSupId)
				.orElseThrow(() -> new ResourceNotFoundException("Info supplémentaire", "Info Id", infosSupId));
		return info;
	}

	@Override
	public List<InfosSup> getAllInfosSup(Patient patient) {
		return infoSupRepo.findByPatient(patient);
	}

	@Override
	public Collection<PriseParametreSoin> addNewParamToPatient(Collection<PriseParametreSoin> params,
			String patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PriseParametreSoin> getParamSoinsByPatient(String patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupePatient> getAllGroupesPatients() {
		return this.groupepatientRepo.findAll();
	}

	@Override
	public Patient getPatientByQrCode(QrCodePatient qrCodePatient) {
		// recuperons le patient à partir du qrcode patient
		return getPatientById(qrCodePatient.getPatient().getPatientId());
	}

	@Override
	public void addNewQrCodePatient(QrCodePatient qrCodePatient, String patientId) {
		// recupérer le patient dont on veut créer le qrcode
		Patient patient = getPatientById(patientId);

		// enregistrement du qrcode du patient
		if (qrCodePatient != null && patient != null) {
			// generation du QrCode du patient
			String qrCodedata = getQRCode(patient);

			qrCodePatient.setEncodedQRCode(qrCodedata);
			qrCodePatient.setPatient(patient);

			this.qrCodePatientRepo.save(qrCodePatient);
		}

	}

	@Override
	public QrCodePatient getQrCodePatientById(Long id) {
		QrCodePatient qrCodePatient = this.qrCodePatientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("QRcode Patient", "QRcode Patient Id", 0));
		return qrCodePatient;
	}

	@Override
	public QrCodePatient getQrCodeByPatient(Patient patient) {
		return qrCodePatientRepo.findByPatient(patient);
	}

	// méthode de génération de qr code
	public String getQRCode(Patient patient) {
		String qrCodeText = patient.getPatientId();
		byte[] image = new byte[0];
		// String filepath = "hims/resources/static/images/";

		try {

			// Generate and Return Qr Code in Byte Array
			image = QrCodeGenerator.getQRCodeImage(qrCodeText, 250, 250);

			// Generate and Save Qr Code Image in static/image folder
			// QrCodeGenerator.generateQRCodeImage(qrCodeText, 250, 250, filepath +
			// "patient_" + qrCodeText + ".png");

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}

		// Convert Byte Array into Base64 Encode String
		String qrcode = Base64.getEncoder().encodeToString(image);

		return qrcode;
	}

	// méthode de fusion de deux patients
	private void fusionnerDeuxPatients(Patient patientPrincipal, Patient patientDoublon) {
		// Fusionner les informations supplémentaires
		patientPrincipal.getInfosSup().addAll(patientDoublon.getInfosSup());

		// Supprimer le patient doublon
		// on supprime d'abord le Qrcode du patient en doublon
		QrCodePatient qrcodePatient = getQrCodeByPatient(patientDoublon);
		qrCodePatientRepo.delete(qrcodePatient);
		
		patientRepo.delete(patientDoublon);
	}
}
