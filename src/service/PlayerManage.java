package service;

import etity.Player;
import etity.Project;

import java.util.List;

public interface PlayerManage {
    public boolean takePart(Player player, Project project);
    public boolean recordResult(Player player, Project project, String score);
    public boolean dropOutGame(Player player, Project project);
}
