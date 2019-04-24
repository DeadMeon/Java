import java.util.Scanner;

public class Dico {
    private ABR tete;
    private ABR courant;
    private String texte;
    private static int auto = 1;
    private static Scanner scanner;


    ///contructor


    public Dico(){
        this.tete = null;
        this.courant = null;
    }

    public Dico(ABR _tete){
        this.tete = _tete;
        this.courant = _tete;
    }


    ///Setter and Getter


    public ABR getTete(){
        return tete;
    }

    public ABR getCourant(){
        return courant;
    }

    public Object getContenuCourant(){
        return courant.getElement().getText();
    }

    public Object enTete(){
        return tete.getElement().toString();
    }

    public void setTete(ABR tete) {
        this.tete = tete;
    }

    public void setCourant(ABR courant) {
        this.courant = courant;
    }

    public void razCourant(){
        courant = tete;
    }

    public boolean estVide(){
        if (courant == null){
            return true;
        }
        return false;
    }

    public boolean possedeSuivantSup(){
        if (courant.getSuivantSup() == null){
            return false;
        } else {
            return true;
        }
    }

    public boolean possedeSuivantInf(){
        if (courant.getSuivantInf() == null){
            return false;
        } else {
            return true;
        }
    }

    public boolean possedePrecedent(){
        if (courant.getPrecedent() == null){
            return false;
        } else {
            return true;
        }
    }

    public void Precedent() {
        if (possedePrecedent()) {
            setCourant(courant.getPrecedent());
        }
    }

    public void SuivantSup() {
        if (possedeSuivantSup()) {
            setCourant(courant.getSuivantSup());
        }
    }

    public void SuivantInf() {
        if (possedeSuivantInf()) {
            setCourant(courant.getSuivantInf());
        }
    }


    ///methode


    public ABR chercher(int cle) {
        if (cle == getCourant().getElement().getCle()) {
            ABR arbre = getCourant();
            razCourant();
            return arbre;
        } else if (cle < getCourant().getElement().getCle() && possedeSuivantInf()) {
            SuivantInf();
            return chercher(cle);
        } else if (cle > getCourant().getElement().getCle() && possedeSuivantSup()) {
            SuivantSup();
            return chercher(cle);
        } else
            razCourant();
            return null;
    }


    public void insert(Element o) {
        if (getTete() == null) {
            setTete(new ABR(o));
            setCourant(getTete());
        } else if (o.getCle() < getCourant().getElement().getCle() && possedeSuivantInf()) {
            SuivantInf();
            insert(o);
        } else if (o.getCle() < getCourant().getElement().getCle()) {
            ABR nouvelle = new ABR(o);
            getCourant().setSuivantInf(nouvelle);
            nouvelle.setPrecedent(getCourant());
            razCourant();
        } else if (o.getCle() > getCourant().getElement().getCle() && possedeSuivantSup()) {
            SuivantSup();
            insert(o);
        } else if (o.getCle() > getCourant().getElement().getCle()) {
            ABR nouvelle = new ABR(o);
            getCourant().setSuivantSup(nouvelle);
            nouvelle.setPrecedent(getCourant());
            razCourant();
        } else {
            System.err.println("Erreur dans l'insert :\n" + o);
        }
    }



    public void supprimer(int cle) {
        ABR aSupprimer = chercher(cle);
        if (aSupprimer != null) {
            if (aSupprimer.getSuivantInf() == null && aSupprimer.getSuivantSup() == null) {
                if (aSupprimer.getPrecedent() == null) {
                    if (aSupprimer == aSupprimer.getPrecedent().getSuivantInf())
                        aSupprimer.getPrecedent().setSuivantInf(null);
                    if (aSupprimer == aSupprimer.getPrecedent().getSuivantSup())
                        aSupprimer.getPrecedent().setSuivantSup(null);
                } else
                    setTete(null);
                aSupprimer = null;

            } else if (aSupprimer.getSuivantInf() == null) {
                if (aSupprimer.getPrecedent() == null) {
                    if (aSupprimer == aSupprimer.getPrecedent().getSuivantInf())
                        aSupprimer.getPrecedent().setSuivantInf(aSupprimer.getSuivantSup());
                    if (aSupprimer == aSupprimer.getPrecedent().getSuivantSup())
                        aSupprimer.getPrecedent().setSuivantSup(aSupprimer.getSuivantSup());
                } else
                    setTete(aSupprimer.getSuivantSup());
                aSupprimer = null;

            } else if (aSupprimer.getSuivantSup() == null) {
                if (aSupprimer.getPrecedent() == null) {
                    if (aSupprimer == aSupprimer.getPrecedent().getSuivantInf())
                        aSupprimer.getPrecedent().setSuivantInf(aSupprimer.getSuivantInf());
                    if (aSupprimer == aSupprimer.getPrecedent().getSuivantSup())
                        aSupprimer.getPrecedent().setSuivantSup(aSupprimer.getSuivantInf());
                } else
                    setTete(aSupprimer.getSuivantSup());
                aSupprimer = null;
            } else {
                ABR nouveau = aSupprimer.getSuivantInf();
                while (nouveau.getSuivantSup() != null)
                    nouveau = nouveau.getSuivantSup();

                if (nouveau.getSuivantInf() != null) {
                    nouveau.getSuivantInf().setPrecedent(nouveau.getPrecedent());
                    nouveau.getPrecedent().setSuivantInf(nouveau.getSuivantInf());
                }
                nouveau.setSuivantSup(aSupprimer.getSuivantSup());
                nouveau.getSuivantSup().setPrecedent(nouveau);
                nouveau.setSuivantInf(aSupprimer.getSuivantInf());
                nouveau.getSuivantInf().setPrecedent(nouveau);
                if (aSupprimer.getPrecedent() == null){
                    nouveau.getPrecedent().setSuivantSup(null);
                    nouveau.setPrecedent(null);
                    setTete(nouveau);
                    aSupprimer = null;
                } else {
                    nouveau.setPrecedent(aSupprimer.getPrecedent());
                    nouveau.getPrecedent().setSuivantInf(nouveau);
                    aSupprimer = null;
                }
            }

            razCourant();
        }
    }

    public void afficher(){
        if (possedeSuivantInf()) {
            SuivantInf();
            afficher();
        }
        System.out.println(getCourant().getElement());
        if (possedeSuivantSup()) {
            SuivantSup();
            afficher();
        }
        Precedent();
    }





    ///Main


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        //System.out.println("Mode auto ? (O/N)");
        //String str = scanner.nextLine();
        //if (str.charAt(0) == 'N' || str.charAt(0) == 'n')
        //    auto = 0;



        Dico a = new Dico();

        if (auto == 1) {
            a.insert(new Element(12));
            a.insert(new Element(25));
            a.insert(new Element(7));
            a.insert(new Element(9));
            a.insert(new Element(11));
            a.insert(new Element(4));
            a.insert(new Element(1));
            a.insert(new Element(16));


            a.afficher();

            a.supprimer(12);
            System.out.println(a.chercher(9));
        } else {


        }
        a.afficher();
    }


}
