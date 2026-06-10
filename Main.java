import java.util.*;

class SocialNetwork {
    private Map<String, List<String>> network = new HashMap<>();

    public void addUser(String user) {
        network.putIfAbsent(user, new ArrayList<>());
    }

    public void addFriendship(String user1, String user2) {
        network.get(user1).add(user2);
        network.get(user2).add(user1);
    }

    public void displayNetwork() {
        System.out.println("\nSocial Network:");
        for (String user : network.keySet()) {
            System.out.println(user + " -> " + network.get(user));
        }
    }

    public void mutualFriends(String user1, String user2) {
        Set<String> s1 = new HashSet<>(network.get(user1));
        Set<String> s2 = new HashSet<>(network.get(user2));
        s1.retainAll(s2);
        System.out.println("\nMutual Friends: " + s1);
    }

    public void suggestFriends(String user) {
        Set<String> suggestions = new HashSet<>();
        for (String friend : network.get(user)) {
            for (String fof : network.get(friend)) {
                if (!fof.equals(user) && !network.get(user).contains(fof)) {
                    suggestions.add(fof);
                }
            }
        }
        System.out.println("\nFriend Suggestions: " + suggestions);
    }

    public void shortestPath(String start, String end) {
        Queue<String> q = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        q.add(start);
        visited.add(start);

        while (!q.isEmpty()) {
            String current = q.poll();

            for (String neighbor : network.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    q.add(neighbor);
                }
            }
        }

        List<String> path = new ArrayList<>();
        String cur = end;
        while (cur != null) {
            path.add(cur);
            cur = parent.get(cur);
        }

        Collections.reverse(path);
        System.out.println("\nShortest Path: " + path);
    }

    private void dfs(String user, Set<String> visited) {
        visited.add(user);
        System.out.print(user + " ");
        for (String friend : network.get(user)) {
            if (!visited.contains(friend)) {
                dfs(friend, visited);
            }
        }
    }

    public void detectCommunities() {
        Set<String> visited = new HashSet<>();
        System.out.println("\nCommunities:");
        for (String user : network.keySet()) {
            if (!visited.contains(user)) {
                dfs(user, visited);
                System.out.println();
            }
        }
    }

    public void influencer() {
        String inf = "";
        int max = 0;
        for (String user : network.keySet()) {
            if (network.get(user).size() > max) {
                max = network.get(user).size();
                inf = user;
            }
        }
        System.out.println("\nMost Influential User: " + inf + " (" + max + " connections)");
    }
}

public class Main {
    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();

        sn.addUser("Hanamanth");
        sn.addUser("Rahul");
        sn.addUser("Priya");
        sn.addUser("Kiran");
        sn.addUser("Ram");

        sn.addFriendship("Hanamanth", "Rahul");
        sn.addFriendship("Hanamanth", "Priya");
        sn.addFriendship("Rahul", "Kiran");
        sn.addFriendship("Priya", "Kiran");
        sn.addFriendship("Kiran", "Ram");

        sn.displayNetwork();
        sn.mutualFriends("Hanamanth", "Kiran");
        sn.suggestFriends("Hanamanth");
        sn.shortestPath("Hanamanth", "Ram");
        sn.detectCommunities();
        sn.influencer();
    }
}
