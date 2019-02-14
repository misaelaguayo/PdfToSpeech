public class Main {

    public static void main(String[] args) {
        PdfParser pdf = new PdfParser();
        String pdfText = pdf.text("src/smmTextbook.pdf");

        Speech test = new Speech("cmu-slt-hsmm");
        test.say(pdfText);

    }
}
