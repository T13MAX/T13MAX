package com.atb.aStar;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @Author 呆呆
 * @Datetime 2023/2/21 10:51
 */
public class AStar {

    private static int[][] S_4 = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private static int[][] S_6 = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private static final int WIDTH = 10;

    private static final int HEIGHT = 10;

    private static final char[][] MAP = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#',},
            {'#', '.', '.', '.', '.', '#', '.', '.', '.', '#',},
            {'#', '.', 'S', '.', '.', '#', '.', '.', '.', '#',},
            {'#', '.', '.', '.', '.', '#', '.', '.', '.', '#',},
            {'#', '#', '#', '#', '#', '#', '#', '.', '.', '#',},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#',},
            {'#', '.', '.', '.', 'E', '.', '.', '.', '.', '#',},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#',},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#',},
    };

    private Set<Node> close = new HashSet<>();

    private Set<Node> open = new HashSet<>();

    private LinkedList<Node> path = new LinkedList<>();

    private int startX;

    private int startY;

    private int endX;

    private int endY;


    public AStar(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public static void main(String[] args) {

        AStar aStar = new AStar(2, 3, 4, 7);

        aStar.findPath();

        aStar.printMap();

    }

    private void findPath() {

        Node node = new Node(null, startX, startY, 0.0f);

        while (true) {

            extendRound(node);
            if (open.size() == 0) break;

            node = getBest();
            if (isTarget(node)) {
                makePath(node);
                return;
            }

            close.add(node);
            open.remove(node);
        }

    }

    private void makePath(Node node) {

        for (Node temp = node; temp != null; temp = temp.getParent()) {
            path.add(temp);
        }
    }

    private Node getBest() {

        float dist = Float.MAX_VALUE;

        Node best = null;

        for (Node node : open) {
            float dist1 = getDist(node);
            if (dist1 < dist) {
                best = node;
                dist = dist1;
            }
        }
        return best;
    }

    private void extendRound(Node parent) {

        int[][] s = S_6;

        for (int i = 0; i < s.length; i++) {

            int newX = s[i][0] + parent.getX();
            int newY = s[i][1] + parent.getY();

            if (!isValid(newX, newY)) continue;

            if (inClose(newX, newY)) continue;

            Node newNode = new Node(parent, newX, newY, getCost(parent.getX(), parent.getY(), newX, newY));

            Node oldNode = getNodeByOpen(newX, newY);
            if (oldNode != null) {

                if (oldNode.getDist() > newNode.getDist()) {
                    oldNode.setParent(parent);
                    oldNode.setDist(newNode.getDist());
                }

                continue;
            }

            open.add(newNode);
        }


    }

    private boolean isTarget(Node best) {
        return best.getX() == endX && best.getY() == endY;
    }

    private float getDist(Node node) {

        return (float) (node.getDist() + Math.sqrt((endX - node.getX()) * (endX - node.getX()) + (endY - node.getY()) * (endY - node.getY())));
    }

    private float getCost(int x1, int x2, int y1, int y2) {

        if (x1 == x2 || y1 == y2) {
            return 1.0f;
        }
        return 1.4f;
    }

    private boolean inClose(int x, int y) {
        return close.stream().anyMatch(e -> e.getX() == x && e.getY() == y);
    }

    private Node getNodeByOpen(int x, int y) {
        for (Node node : open) {
            if (node.getX() == x && node.getY() == y)
                return node;
        }
        return null;
    }

    private boolean isValid(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return MAP[y][x] != '#';
    }

    private void printMap() {

        for (Node node : path) {
            MAP[node.getY()][node.getX()] = '*';
        }

        MAP[startY][startX] = 'S';
        MAP[endY][endX] = 'E';

        for (char[] chars : MAP) {
            for (char ch : chars) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }


}

class Node {

    private Node parent;

    private int x;

    private int y;

    private float dist;


    public Node(Node parent, int x, int y, float dist) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getDist() {
        return dist;
    }

    public void setDist(float dist) {
        this.dist = dist;
    }
}
