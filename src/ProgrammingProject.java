import processing.core.PApplet;
import processing.core.PImage;

public class ProgrammingProject extends PApplet{
    public static PApplet processing;
    public static void main(String... args){
        PApplet.main("ProgrammingProject");
    }
    private Color color = new Color(0,0,0) {
        @Override
        public int[] get() {
            return super.get();
        }
    };

//    SinOsc sine100;
//   public static SoundFile[] sounds;
//    SawOsc saw40;
//    public static WhiteNoise noise;
//    public static LowPass lowPass;
//    private PImage youDied, woop, fade;
//    private boolean t;
//    int i = 0;
//    private GameInstance game;

    public PObject[] buttone = new PObject[5];

    public void settings(){
        size(800,800);
    }

    public void setup(){

        processing = this;
        //dataPath(DATA_PATH);
//        youDied = loadImage("youdead.png");
//        woop = loadImage("woop2.png");
//        fade = loadImage("screen_fader.png");
//        youDied.resize(width, height);
//        woop.resize(width, height);
//        fade.resize(width, height);
//        t = true;
//        game = new GameInstance();
        Color[] colors = new Color[]{new Color(255,100,100) {
            @Override
            public int[] get() {
                return super.get();
            }
        },new Color(100,255,100) {
            @Override
            public int[] get() {
                return super.get();
            }
        },new Color(100,100,255) {
            @Override
            public int[] get() {
                return super.get();
            }
        }};
        for(int yOffset =1; yOffset<= buttone.length; yOffset++){
            buttone[yOffset-1] = new ImagePObject(new Vector(width/2, (80+20)*yOffset), 80, 80,  loadImage("nuke.png"));
            buttone[yOffset-1].setAsngle((float) (-Math.PI/2));
            if((yOffset-1)<colors.length){
               buttone[yOffset-1].setColour(colors[yOffset-1]);
            }
        }
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
        int[] c = color.get();
        background(c[0],c[1],c[2]);
        for(int i=0; i<buttone.length; i++) {
            buttone[i].tick(new Force(new Vector(0,0),0,0));
            if(buttone[i].mouseOver()&&!(buttone[i] instanceof Explosion)){
                buttone[i].cloneHitbox().show();
                if(mousePressed) {
                    Color color = buttone[i].getColour();
                    buttone[i] = new Explosion(buttone[i].W, 200, buttone[i], 0.005f, 128, ProgrammingProject.processing.loadImage("boom_3.png"));
                    buttone[i].setAsngle((float) (-Math.PI / 2));
                    buttone[i].setColour(color);
                    color = buttone[i].getColour();
                }
            }
            if(buttone[i].toDeleat()) {
                Color color = buttone[i].getColour();
                buttone[i] = new ImagePObject(buttone[i].position, 80, 80,  loadImage("nuke.png"));
                buttone[i].setAsngle((float) (-Math.PI/2));
                buttone[i].setColour(color);
            }
        }
//        int state = 0;
//        if((state = game.state())==0){
//            background(0);
//            image(fade,0,0);
//            game.tick();
//        }else{
//            if((i++)%5==0){
//                image(fade,0,0);
//                game.tick();
//                if(state == 1){
//                    image(youDied,0,0);
//                }else{
//                    image(woop,0,0);
//                }
//            }
//            if(t){
//                //sine100.stop();
//                //saw40.stop();
//                //noise.stop();
//                t=false;
//                if(state == 1){
//                    //sounds[1].play();
//                }else{
//                }
//            }
//        }
    }
}
