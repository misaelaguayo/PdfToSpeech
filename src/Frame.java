import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Frame extends JFrame{

    public Frame(){
        JLabel startLabel = new JLabel("Starting page");
        JLabel endLabel = new JLabel("Ending page");
        JTextField startField = new JTextField();
        JTextField endField = new JTextField();
        JButton start = new JButton("Play");
        JButton stop = new JButton("Stop");
        JButton file = new JButton("File");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(startLabel)
                .addComponent(endLabel))
            .addGroup(layout.createParallelGroup()
                .addComponent(startField)
                .addComponent(endField))
            .addGroup(layout.createParallelGroup()
                .addComponent(start)
                .addComponent(stop)
                .addComponent(file))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, stop, start, file);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(startLabel)
                .addComponent(startField)
                .addComponent(start))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(endLabel)
                .addComponent(endField)
                .addComponent(stop))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(file))
        );
        setTitle("Play PDF");
        setPreferredSize(new Dimension(300,135));

        passValues(file, startField, endField, start, stop);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void passValues(JButton file, JTextField startField, JTextField endField, JButton start, JButton stop){
        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pdfText;
                final JFileChooser fc = new JFileChooser();
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    init(file, startField, endField, start, stop);
                }
                else{
                    System.out.println("No file was selected");
                }

            }
        });

    }
    public void init(File file, JTextField startField, JTextField endField, JButton startButton, JButton stopButton){

        PdfParser pdf = new PdfParser();


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String start = startField.getText();
                String end = startField.getText();
                String pdfText = pdf.text(file,Integer.parseInt(start),Integer.parseInt(end));

                Speech test = new Speech("cmu-slt-hsmm");

                try {
                    test.say(pdfText);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
