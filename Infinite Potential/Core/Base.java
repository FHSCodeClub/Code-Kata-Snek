import java.util.ArrayList;

public abstract class Base{
    
    static final int costOfPlayer = 15;
    static final int costOfExtractor = 5;
    static final int costOfConveyor = 1;
    
    private int gold;
    private ArrayList<Player> allPlayers;
    private ArrayList<Extractor> allExtractors;
    private ArrayList<Conveyor> allConveyors;
    
    public abstract void run();

    public final int getGold(){
        return gold;
    }
    
    public final void addPlayer(){
        if(gold < costOfPlayer) return;
        allPlayers.add(new Player());
        gold -= costOfPlayer;
    }

    public final void addExtractor(){
        if(gold < costOfExtractor) return;
        allExtractors.add(new Extractor());
        gold -= costOfExtractor;
    }
    
    public final void addConveyor(Player player){
        if(gold < costOfConveyor) return;
        allConveyors.add(new Conveyor());
        gold -= costOfConveyor;
    }

    public final ArrayList<Player> getPlayers(){
        return allPlayers.clone();
    }

    public final ArrayList<Extractor> getExtractors(){
        return allExtractors.clone();
    }

    public final ArrayList<Conveyor> getConveyors(){
        return allConveyors().clone();
    }

}
