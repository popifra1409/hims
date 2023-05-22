package co.iaf.service.pharmacie;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import co.iaf.dao.pharmacie.ProduitRepository;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduit(Produit produit) {
		// TODO Auto-generated method stub

	}

	@Override
	public Produit getProduitById(String produitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduits() {
		// TODO Auto-generated method stub
		return null;
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
