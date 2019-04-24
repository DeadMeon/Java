public class ABR {
    private Element element;
    private ABR suivantSup = null;
    private ABR suivantInf = null;
    private ABR precedent = null;



    public ABR(Element element, ABR suivantSup, ABR suivantInf, ABR precedent) {
        this.element = element;
        this.suivantSup = suivantSup;
        this.suivantInf = suivantInf;
        this.precedent = precedent;
    }

    public ABR(Element element, ABR suivantSup, ABR suivantInf) {
        this.element = element;
        this.suivantInf = suivantInf;
        this.suivantSup = suivantSup;
    }

    public ABR(Element element) {
        this.element = element;
    }

    public ABR() {
    }



    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public ABR getSuivantSup() {
        return suivantSup;
    }

    public void setSuivantSup(ABR suivantSup) {
        this.suivantSup = suivantSup;
    }

    public ABR getSuivantInf() {
        return suivantInf;
    }

    public void setSuivantInf(ABR suivantInf) {
        this.suivantInf = suivantInf;
    }

    public ABR getPrecedent() {
        return precedent;
    }

    public void setPrecedent(ABR precedent) {
        this.precedent = precedent;
    }


    @Override
    public String toString() {
        String str = " Comtenue \n element : " + getElement();
        if (getSuivantSup() != null)
            str += "\n suivantSup : " + getSuivantSup().getElement().toString();
        if(getSuivantInf() != null)
            str += "\n suivantInf : " + getSuivantInf().getElement().toString();
        if(getPrecedent() != null)
            str += "\n precedent : " + getPrecedent().getElement().toString();

        return  str ;

    }
}
