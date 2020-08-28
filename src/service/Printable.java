package service;

public interface Printable {
    public void printProjectList();
    public void printPlayerList();
    public boolean printCertainProject(String projectName);
    public boolean printCertainPlayer(String playerName);
    public void printMenu(int type);
}
