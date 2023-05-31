package co.iaf.service.pharmacie;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.pharmacie.GroupeProduitRepository;
import co.iaf.dao.pharmacie.ProduitRepository;
import co.iaf.dao.pharmacie.QrCodeProduitRepository;
import co.iaf.entity.pharmacie.Produit;
import co.iaf.entity.pharmacie.QrCodeProduit;
import co.iaf.utils.QrCodeGenerator;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

	private ProduitRepository produitRepo;
	private QrCodeProduitRepository qrCodeProdRepo;
	private GroupeProduitRepository groupeProduitRepo;

	public ProduitServiceImpl(ProduitRepository produitRepo, QrCodeProduitRepository qrCodeProdRepo,
			GroupeProduitRepository groupeProduitRepo) {
		super();
		this.produitRepo = produitRepo;
		this.qrCodeProdRepo = qrCodeProdRepo;
		this.groupeProduitRepo = groupeProduitRepo;
	}

	@Override
	public Produit addNewProduit(Produit produit) {
		Produit prod = this.produitRepo.save(produit);
		// on genere le qrCode du produit
		addNewQrCode(new QrCodeProduit(), prod.getReference());
		return prod;
	}

	@Override
	public Produit updateProduit(String produitId, Produit produit) {
		Produit prod = getProduitById(produitId);
		prod.setDesignation(produit.getDesignation());
		prod.setPrixUnit(produit.getPrixUnit());
		prod.setSeuilReappro(produit.getSeuilReappro());
		prod.setQteReappro(produit.getQteReappro());
		prod.setStockAlerte(produit.getStockAlerte());
		prod.setCoefUnite(produit.getCoefUnite());
		prod.setDescription(produit.getDescription());
		prod.setArchive(produit.isArchive());
		prod.setDesactive(produit.isDesactive());
		prod.setDiponible(produit.isDiponible());
		prod.setGroupeProduit(produit.isGroupeProduit());
		prod.setRefrigere(produit.isRefrigere());
		prod.setSensibilite(produit.getSensibilite());
		prod.setDci(produit.getDci());
		prod.setNomCommercial(produit.getNomCommercial());
		prod.setLiaisonPrestation(produit.isLiaisonPrestation());
		prod.setPMUP(produit.isPMUP());
		prod.setStockTheorique(produit.getStockTheorique());

		return this.produitRepo.save(prod);
	}

	@Override
	public void deleteProduit(Produit produit) {
		this.produitRepo.delete(produit);
	}

	@Override
	public Produit getProduitById(String reference) {
		Produit produit = this.produitRepo.findById(reference)
				.orElseThrow(() -> new ResourceNotFoundException("Produit", "Produit reference", 0));
		return produit;
	}

	@Override
	public List<Produit> getAllProduits() {
		return this.produitRepo.findAll();
	}

	@Override
	public void addNewQrCode(QrCodeProduit qrCodeProduit, String refprod) {
		// recuperation du produit concerné
		Produit produit = getProduitById(refprod);
		if (produit != null) {
			String qrcode = getQRCode(produit.getReference());
			qrCodeProduit.setData(qrcode);
			qrCodeProduit.setProduit(produit);
			this.qrCodeProdRepo.save(qrCodeProduit);
		}
	}

	@Override
	public String getQRCode(String reference) {
		byte[] image = new byte[0];
		try {
			// Generate and Return Qr Code in Byte Array
			image = QrCodeGenerator.getQRCodeImage(reference, 250, 250);

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}

		// Convert Byte Array into Base64 Encode String
		String qrcode = Base64.getEncoder().encodeToString(image);
		return qrcode;
	}

	@Override
	public String getQrCodeProduit(Produit produit) {
		QrCodeProduit qrCodeProduit = this.qrCodeProdRepo.findByProduit(produit);
		String qrcode = qrCodeProduit.getData();

		return qrcode;
	}

	@Override
	public Produit getByQrCodeProduit(Long qrcodeId) {
		QrCodeProduit qrCodeProduit = getQrCodeById(qrcodeId);
		
		return qrCodeProduit.getProduit();
	}

	@Override
	public QrCodeProduit getQrCodeById(Long id) {
		QrCodeProduit qrCodeProduit = this.qrCodeProdRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("QrCodeProduit", "QrCodeProduit id", 0));
		return qrCodeProduit;
	}
}
