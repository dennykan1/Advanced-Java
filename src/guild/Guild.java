package guild;

import java.util.ArrayList;
import java.util.List;

public class Guild {
    private List<Player> roster;
    private String name;
    private int capacity;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player) {

        if (this.roster.size() < this.capacity) {
            this.roster.add(player);
        }
    }

    public boolean removePlayer(String name) {
        int index = getIndexOfPlayer(name);

        if (index != -1) {
            this.roster.remove(index);
            return true;
        }
        return false;
    }

    public void promotePlayer(String name) {
        int index = getIndexOfPlayer(name);

        if (index != -1) {
            this.roster.get(index).setRank("Member");
        }
    }

    public void demotePlayer(String name) {
        int index = getIndexOfPlayer(name);

        if (index != -1) {
            this.roster.get(index).setRank("Trial");
        }

    }

    public Player[] kickPlayersByClass(String clazz) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < this.roster.size(); i++) {
            if (this.roster.get(i).getClazz().equals(clazz)) {
                players.add(this.roster.get(i));
                this.roster.remove(i);
                i--;
            }
        }

        Player[] removedPlayer = new Player[players.size()];

        for (int i = 0; i < players.size(); i++) {
            removedPlayer[i] = players.get(i);
        }

        return removedPlayer;
    }

    public int count() {
        return this.roster.size();
    }

    /**
     * WARNING!!! CHECK THIS METHOD !!!
     */

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Players in the guild: %s:%n", this.name));
        this.roster.forEach(e -> sb.append(e.toString()).append(String.format("%n")));
        return sb.toString();
    }


    private int getIndexOfPlayer(String name) {
        for (int i = 0; i < this.roster.size(); i++) {
            if (this.roster.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
