public class Element {
    private int cle;
    private String text;

    public Element(int i, String string){
        cle = i;
        text = string;
    }

    public Element(int i){
        cle = i;
        text = "#" + i;
    }

    public Element(){
        cle = 0;
        text = "";
    }

    public int getCle() {
        return cle;
    }

    public String getText() {
        return text;
    }

    public void setElement(Element e) {
        this.setCle(e.getCle());
        this.setText(e.getText());
    }

    public void setCle(int i) {
        cle = i;
    }

    public void setText(String string) {
        text = string;
    }

    public String toString() {
        return "Cle : " + getCle() + "; Contenu : " + getText();
    }
}