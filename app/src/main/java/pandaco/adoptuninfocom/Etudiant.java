package pandaco.adoptuninfocom;

/**
 * Created by tichador on 09/03/2016.
 */
public class Etudiant {

    private int id_etudiant;
    private String nom_etudiant;
    private String prenom_etudiant;
    private String sexe_etudiant;
    private String cp;
    private String ville;
    private String datenaiss;
    private String tel_etudiant;
    private String mdp_etudiant;
    private String description_etudiant;

    public Etudiant(){

    }

    public Etudiant(String nom_etudiant, String prenom_etudiant, String sexe_etudiant, String datenaiss, String tel_etudiant, String mdp_etudiant, String description_etudiant) {
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.sexe_etudiant = sexe_etudiant;
        this.datenaiss = datenaiss;
        this.tel_etudiant = tel_etudiant;
        this.mdp_etudiant = mdp_etudiant;
        this.description_etudiant = description_etudiant;
    }

    public Etudiant(String nom_etudiant, String prenom_etudiant, String sexe_etudiant, String cp, String datenaiss, String tel_etudiant, String mdp_etudiant, String description_etudiant) {
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.sexe_etudiant = sexe_etudiant;
        this.cp= cp;
        this.datenaiss = datenaiss;
        this.tel_etudiant = tel_etudiant;
        this.mdp_etudiant = mdp_etudiant;
        this.description_etudiant = description_etudiant;
    }

    public Etudiant(String nom_etudiant, String prenom_etudiant, String sexe_etudiant, String cp, String ville, String datenaiss, String tel_etudiant, String mdp_etudiant, String description_etudiant) {
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.sexe_etudiant = sexe_etudiant;
        this.cp= cp;
        this.ville= ville;
        this.datenaiss = datenaiss;
        this.tel_etudiant = tel_etudiant;
        this.mdp_etudiant = mdp_etudiant;
        this.description_etudiant = description_etudiant;
    }

    public int getId() {

        return id_etudiant;
    }

    public void setId(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public String getNom_etudiant() {
        return nom_etudiant;
    }

    public void setNom_etudiant(String nom_etudiant) {
        this.nom_etudiant = nom_etudiant;
    }

    public String getPrenom_etudiant() {
        return prenom_etudiant;
    }

    public void setPrenom_etudiant(String prenom_etudiant) {
        this.prenom_etudiant = prenom_etudiant;
    }
    public String getSexe_etudiant() {
        return sexe_etudiant;
    }

    public void setSexe_etudiant(String sexe_etudiant) {
        this.sexe_etudiant = sexe_etudiant;
    }
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(String datenaiss) {
        this.datenaiss = datenaiss;
    }
    public String getTel_etudiant() {
        return tel_etudiant;
    }

    public void setTel_etudiant(String tel_etudiant) {
        this.tel_etudiant = tel_etudiant;
    }
    public String getMdp_etudiant() {
        return mdp_etudiant;
    }

    public void setMdp_etudiant(String mdp_etudiant) {
        this.mdp_etudiant = mdp_etudiant;
    }

    public String getDescription_etudiant() {
        return description_etudiant;
    }

    public void setDescription_etudiant(String description_etudiant) {this.description_etudiant = description_etudiant;
    }


}
