//Roll:2007045- Md Babla Islam
// have used bridge & proxy design pattern to solve the problem

interface TV{
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int vol);
    int getChannel();
    void setChannel(int channel);

}








class SmartTV implements TV{
    boolean powerOn=false;
    int volume=10;
    int channel=1;




    @Override
    public boolean isEnabled() {
        return powerOn;
    }




    @Override
    public void enable() {
        powerOn=true;
    }




    @Override
    public void disable() {
        powerOn=false;
    }




    @Override
    public int getVolume() {
        return volume;
    }
    
    
    
    

    @Override
    public void setVolume(int n) {
        volume=n;
    }
    
    
    
    
    

    @Override
    public int getChannel() {
        return channel;
    }
    
    
    
    

    @Override
    public void setChannel(int ch) {
        channel=ch;
    }
    
    
    
    public void Youtube(YoutubeClass utb){
        System.out.println("Youtube for SmartTV is called");
        utb.runYoutube();
    }
}






class GeneralTV implements TV{
    boolean powerOn=false;
    int volume=20;
    int channel=5;




    @Override
    public boolean isEnabled() {
        return powerOn;
    }
    
    
    
    

    @Override
    public void enable() {
        powerOn=true;
    }
    
    
    
    

    @Override
    public void disable() {
        powerOn=false;
    }
    
    
    
    
    

    @Override
    public int getVolume() {
        return volume;
    }
    
    
    
    

    @Override
    public void setVolume(int n) {
        volume=n;
    }





    @Override
    public int getChannel() {
        return channel;
    }




    @Override
    public void setChannel(int ch) {
        channel=ch;
    }
}









class Remote{
    protected TV tv;

    Remote(){tv=null;}
    Remote(TV tv){
        this.tv=tv;
    }
    
    
    
    
    public void togglePower() {
        if(tv.isEnabled()){
            System.out.println("TV power turned off");
            tv.disable();
        }
        else {
            
            System.out.println("TV power turned on");
            tv.enable();
        }
    }






    public void volumeUp() {
        if(tv.isEnabled()){
            tv.setVolume(tv.getVolume()+1);
            System.out.println("Increasing volume +1");
        }else{
            System.out.println("Turn on tv first");
        }
    }
    
    
    
    

    public void volumeDown() {
        if(tv.isEnabled()){
            tv.setVolume(tv.getVolume()-1);
            System.out.println("Decreasing volume -1");
        }else{
            System.out.println("Turn on tv first");
        }

    }
    
    
    

    public void channelUp() {
        if(tv.isEnabled()){
            tv.setChannel(tv.getChannel()+1);
            System.out.println("Channel=channel+1");
        }else{
            System.out.println("Turn on tv first");
        }


    }
    
    
    

    public void channelDown() {
        if(tv.isEnabled()){
            tv.setChannel(tv.getChannel()-1);
            System.out.println("Channel=channel-1");
        }else{
            System.out.println("Turn on tv first");
        }

    }
}





class SmartRemote extends Remote{

    SmartRemote(){

    }
    SmartRemote(TV tv){
        super(tv);
    }
    void showYoutube(YoutubeClass yt){
        ((SmartTV)tv).Youtube(yt);
    }
}




//YoutubeCLass implements Proxy Design method
interface YoutubeClass{
    void runYoutube();
}
class FirstLoadYoutube implements YoutubeClass{

    @Override
    public void runYoutube() {
        System.out.println("Running youtube");
    }
}

class proxyLoadYoutube implements YoutubeClass{
    private FirstLoadYoutube firstLoadYoutube;
    @Override
    public void runYoutube() {
        if(firstLoadYoutube==null){
            firstLoadYoutube=new FirstLoadYoutube();
            System.out.println("Starting Youtube");
        }
        firstLoadYoutube.runYoutube();
    }
}

public class Main {
    public static void main(String[] args) {


        //For GeneralTV
        GeneralTV gtv=new GeneralTV();
        Remote remote= new Remote(gtv);
        System.out.println("General Tv");
        remote.togglePower();
        remote.volumeUp();
        remote.channelUp();
        remote.channelDown();
        remote.volumeDown();







        //For SmartTV
        System.out.println();
        System.out.println("Smart Tv");
        SmartTV stv=new SmartTV();
        SmartRemote sremote= new SmartRemote(stv);
        sremote.togglePower();
        sremote.volumeUp();
        sremote.channelUp();
        sremote.channelDown();
        sremote.volumeDown();
        
        
        
        
        
        //Youtube Class
        YoutubeClass yt=new proxyLoadYoutube();



        //Accessing Youtube() using smart-remote.
        sremote.showYoutube(yt);
        sremote.showYoutube(yt);
    }

}