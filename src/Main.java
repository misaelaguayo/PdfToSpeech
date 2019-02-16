import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Speech test = new Speech("cmu-slt-hsmm");
        String pdfText = "";

        JFrame f = new JFrame("PDF to speech program");
        f.setSize(500,300);
        f.setLocation(300,200);
        final JFileChooser fc = new JFileChooser();
        final JButton button = new JButton("Play PDF");
        f.getContentPane().add(BorderLayout.CENTER, fc);
        f.getContentPane().add(BorderLayout.SOUTH, button);

        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            PdfParser pdf = new PdfParser();
            pdfText = pdf.text(file,0,1);
        }
        else{
            System.out.println("No file was selected");
        }

        final String maryOutput = pdfText;

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                test.say(maryOutput);
            }
        });

        f.setVisible(true);

    }
}
