import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import java.util.concurrent.TimeUnit;

public class Speech {

    private MaryInterface marytts;
    private AudioPlayer ap;

    public Speech(String voiceName)
    {
        try
        {
            marytts = new LocalMaryInterface();
            marytts.setVoice(voiceName);
        }
        catch(MaryConfigurationException ex)
        {
            ex.printStackTrace();
        }
    }


    public  void say(String input) throws InterruptedException, SynthesisException, MaryConfigurationException
    {
        int interval = 500;
        int arrayLength = (int) Math.ceil(((input.length() / (double) interval)));
        String pageInput = new String();
        String[] result = new String[arrayLength];

        int j = 0;
        int lastIndex = result.length -1;
        //Memory will run out if you try to process an entire pdf book
        // iterate through intervals of the book to save memory
        for (int i = 0; i <= lastIndex; i++){
            pageInput = input.substring(j, j+interval);
            j += interval;

            syncSay(pageInput);
        }

    }

    public synchronized void syncSay(String input) throws InterruptedException{

        try{
            AudioPlayer ap = new AudioPlayer();
            AudioInputStream audio = marytts.generateAudio(input);
            ap.setAudio(audio);
            ap.start();
            ap.join();

            System.out.println(input);
        }
        catch(SynthesisException ex){
            System.err.println("Error saying phrase");
        }

    }


}
