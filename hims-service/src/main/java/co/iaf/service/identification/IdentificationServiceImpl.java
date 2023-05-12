package co.iaf.service.identification;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import co.iaf.dao.admission.PriseParametreSoinRepository;
import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.identification.GroupePatientRepository;
import co.iaf.dao.identification.InfoSupRepository;
import co.iaf.dao.identification.PatientRepository;
import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.identification.GroupePatient;
import co.iaf.entity.identification.InfosSup;
import co.iaf.entity.identification.Patient;
import co.iaf.utils.BarCodeGenerator;
import co.iaf.utils.QrCodeGenerator;

@Service
@Transactional
public class IdentificationServiceImpl implements IdentificationService {

	// private static final String QR_CODE_IMAGE_PATH = "/img/";

	private PatientRepository patientRepo;
	private InfoSupRepository infoSupRepo;
	private GroupePatientRepository groupepatientRepo;

	public IdentificationServiceImpl(PatientRepository patientRepo, InfoSupRepository infoSupRepo,
			GroupePatientRepository groupepatientRepo) {
		super();
		this.patientRepo = patientRepo;
		this.infoSupRepo = infoSupRepo;
		this.groupepatientRepo = groupepatientRepo;
	}

	@Override
	public Patient addNewPatient(Patient patient) {
		Collection<InfosSup> infosupp = patient.getInfosSup();

		Patient pat = this.patientRepo.save(patient);
		/*
		 * // on genere le qrCode du patient String qrCodePatient = getQRCode(pat);
		 * pat.setPatientBarCode(qrCodePatient);
		 */
		// on genere le barCode du patient
		pat.setPatientBarCode(getQRCode(pat));

		// informations supplémentaires
		if (infosupp != null) {
			infosupp.forEach(info -> {
				this.infoSupRepo.save(new InfosSup(null, info.getCle(), info.getValeur(), pat));
			});
		}
		return this.patientRepo.save(pat);
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
	public List<Patient> getAllPatients() {
		return this.patientRepo.findAll();
	}

	@Override
	public List<Patient> getPatientsByLastNameOrFirstName(String lastname, String firstname) {
		return this.patientRepo.findByPatientLastNameContainingOrPatientFirstNameContaining(lastname, firstname);
	}

	@Override
	public Patient getPatientByBarCode(String codebarre) {
		return this.patientRepo.findByPatientBarCode(codebarre);
	}

	@Override
	public Patient mergePatient(String patientPrincipalid, Collection<Long> patientsSecondaireIds) {
		// on recupère le patient principal
		Patient patientPrincipal = getPatientById(patientPrincipalid);
		return null;
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
	public String getQRCode(Patient patient) {
		String qrCodeText = patient.getPatientId();
		byte[] image = new byte[0];
		try {

			// Generate and Return Qr Code in Byte Array
			image = QrCodeGenerator.getQRCodeImage(qrCodeText, 250, 250);

			// Generate and Save Qr Code Image in static/image folder
			// QrCodeGenerator.generateQRCodeImage(qrCodeText, 250, 250, QR_CODE_IMAGE_PATH
			// + qrCodeText + ".png");

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}

		// Convert Byte Array into Base64 Encode String
		String qrcode = Base64.getEncoder().encodeToString(image);

		return qrcode;
	}

	@Override
	public String getBarCode(Patient patient) {
		String barCodeText = patient.getPatientId();
		byte[] image = new byte[0];
		// Generate and Return Bar Code in Byte Array
		image = BarCodeGenerator.getBarCodeImage(barCodeText, 250, 250);

		// Convert Byte Array into Base64 Encode String
		String barcode = Base64.getEncoder().encodeToString(image);

		return barcode;
	}

	@Override
	public List<GroupePatient> getAllGroupesPatients() {
		return this.groupepatientRepo.findAll();
	}

}
