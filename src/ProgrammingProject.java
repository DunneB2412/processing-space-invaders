import processing.core.PApplet;
import processing.core.PImage;

public class ProgrammingProject extends PApplet{
    public static PApplet processing;
    public static void main(String... args){
        PApplet.main("ProgrammingProject");
    }

//    public static SoundFile[] sounds;
//    SinOsc sine100;
//    SawOsc saw40;
//    public static WhiteNoise noise;
//    public static LowPass lowPass;
    private PImage youDied, woop, fade;
    private boolean t;
    int i = 0;

    private GameInstance game;

    public void settings(){
        size(1800,1000);
    }

    public void setup(){
        processing = this;
        youDied = loadImage("src/main/data/youdead.png");
        woop = loadImage("woop2.png");
        fade = loadImage("screen_fader.png");
        youDied.resize(width, height);
        woop.resize(width, height);
        fade.resize(width, height);
        t = true;
        //game = new GameInstance();
        background(0);
//        sounds = new SoundFile[]{new SoundFile(this, "Splat - Gaming Sound Effect (HD).mp3"),new SoundFile(this, "Dark Souls  You Died  Sound Effect.mp3"),
//                new SoundFile(this, "Metal Hit 09 Sound Effects.mp3"),new SoundFile(this, "Missile+1.mp3"),new SoundFile(this, "Explosion+1.mp3"),new SoundFile(this, "Torpedo+Explosion.mp3")};
//        sine100 = new SinOsc(this);
//        sine100.play();
//        sine100.freq(100);
//        sine100.amp(0.3);
//        saw40 = new SawOsc(this);
//        saw40.play();
//        saw40.freq(40);
//        saw40.amp(0.03);
//
//        noise = new WhiteNoise(this);
//        lowPass = new LowPass(this);
//        noise.play();
//        lowPass.process(noise, 100);
    }

    public void draw(){
        int state = 1;
        if(false||(state = game.state())==0){
            background(0);
            //image(fade,0,0);
            game.tick();
        }else{
            if((i++)%5==0){
                image(fade,0,0);
                game.tick();
                if(state == 1){
                    image(youDied,0,0);
                }else{
                    image(woop,0,0);
                }
            }
            if(t){
                //sine100.stop();
                //saw40.stop();
                //noise.stop();
                t=false;
                if(state == 1){
                    //sounds[1].play();
                }else{
                }
            }
        }
    }
}
