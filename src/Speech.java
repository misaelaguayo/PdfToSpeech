import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;

public class Speech {

    private MaryInterface marytts;
    private AudioPlayer ap;

    public Speech(String voiceName)
    {
        try
        {
            marytts = new LocalMaryInterface();
            marytts.setVoice(voiceName);
            ap = new AudioPlayer();
        }
        catch(MaryConfigurationException ex)
        {
            ex.printStackTrace();
        }
    }

    public void say(String input)
    {
        try
        {
            AudioInputStream audio = marytts.generateAudio(input);

            ap.setAudio(audio);
            ap.start();
        }
        catch(SynthesisException ex)
        {
            System.err.println("Error saying phrase." + ex);
        }
    }
}
