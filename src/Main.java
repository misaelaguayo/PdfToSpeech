import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;

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
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JFileChooser fc = new JFileChooser();
        final JButton button = new JButton("Play PDF");

        final JLabel startLabel = new JLabel();
        final JLabel endLabel = new JLabel();
        final JTextField startField = new JTextField();
        final JTextField endField = new JTextField();

        startLabel.setText("Starting page: ");
        endLabel.setText("Ending page: ");

        Box indexFields = Box.createHorizontalBox();
        indexFields.add(startLabel);
        indexFields.add(startField);
        indexFields.add(endLabel);
        indexFields.add(endField);

        f.getContentPane().add(indexFields);
        f.getContentPane().add(BorderLayout.SOUTH, button);

        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            PdfParser pdf = new PdfParser();
            pdfText = pdf.text(file,295,315);
        }
        else{
            System.out.println("No file was selected");
        }

        final String maryOutput = pdfText;

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    test.say(maryOutput);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (SynthesisException e1) {
                    e1.printStackTrace();
                } catch (MaryConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        });

        f.setVisible(true);

    }
}
