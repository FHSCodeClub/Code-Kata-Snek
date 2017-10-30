package Core;

import java.util.ArrayList;

import static EditTheseClasses.TeamWorker.makeTeamWorker;

public abstract class Base{
    
    static final int costOfWorker = 15;
    static final int costOfExtractor = 5;
    static final int costOfConveyor = 1;
    
    private int gold;
    private ArrayList<Worker> allWorkers;
    private ArrayList<Extractor> allExtractors;
    private ArrayList<Conveyor> allConveyors;
    
    public abstract void run();

    public final int getGold(){
        return gold;
    }
    
    public final void addWorker(){
        if(gold < costOfWorker) return;
        allWorkers.add(makeTeamWorker());
        gold -= costOfWorker;
    }

    public final void addExtractor(){
        if(gold < costOfExtractor) return;
        allExtractors.add(new Extractor());
        gold -= costOfExtractor;
    }
    
    public final void addConveyor(){
        if(gold < costOfConveyor) return;
        allConveyors.add(new Conveyor());
        gold -= costOfConveyor;
    }

    public final ArrayList<Worker> getPlayers(){
        return (ArrayList<Worker>) allWorkers.clone();
    }

    public final ArrayList<Extractor> getExtractors(){
        return (ArrayList<Extractor>) allExtractors.clone();
    }

    public final ArrayList<Conveyor> getConveyors(){
        return (ArrayList<Conveyor>) allConveyors.clone();
    }

}
