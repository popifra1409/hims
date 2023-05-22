package co.iaf.service.pharmacie;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.pharmacie.ProduitRepository;
import co.iaf.entity.identification.Patient;
import co.iaf.entity.pharmacie.Produit;
import co.iaf.utils.QrCodeGenerator;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

	private ProduitRepository produitRepo;

	public ProduitServiceImpl(ProduitRepository produitRepo) {
		super();
		this.produitRepo = produitRepo;
	}

	@Override
	public Produit addNewProduit(Produit produit) {
		Produit prod = this.produitRepo.save(produit);
		// on genere le barCode du patient
		prod.setCodebarre(getQRCode(prod));

		return this.produitRepo.save(prod);
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
	public String getQRCode(Produit produit) {
		String qrCodeText = produit.getReference();
		byte[] image = new byte[0];
		try {
			// Generate and Return Qr Code in Byte Array
			image = QrCodeGenerator.getQRCodeImage(qrCodeText, 250, 250);

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}

		// Convert Byte Array into Base64 Encode String
		String qrcode = Base64.getEncoder().encodeToString(image);

		return qrcode;
	}

}
