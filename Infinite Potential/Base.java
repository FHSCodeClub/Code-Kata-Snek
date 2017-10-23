import java.util.ArrayList;

public abstract class Base{
    
    final int costOfPlayer = 5;
    final int costOfConveyor = 1;
    
    private int gold;
    public ArrayList<Player> allPlayers;
    public ArrayList<Extractor> allExtractors;
    public ArrayList<Conveyor> allConveyors;
    
    public abstract void run();
    
    public final void addPlayer(){
        if(gold >= costOfPlayer){
            allPlayers.add(new Player());
        }
    }
    
    public final void addConveyor(Player player){
        if(gold >= costOfConveyor){
            allConveyors.add(new Conveyor(player.getLocation()));
        }
    }
}
