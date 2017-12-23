package aeramli.ma.backbasecitiesapp.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrieNode<T extends TrieItem> {
    private Set<T> items;
    private Map<Character, TrieNode<T>> children;
    private boolean isEnd;

    public TrieNode() {
        this.items = new HashSet<>();
        this.children = new HashMap<>();
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void markAsEnd(T item) {
        isEnd = true;
        items.add(item);
    }

    public Set<T> getItems() {
        return items;
    }

    public List<T> getAllItems() {
        List<T> listItem = new ArrayList<>();
        if (isEnd) {
            listItem.addAll(items);
        }
        if (!children.isEmpty()) {
            for (TrieNode node : children.values()) {
                listItem.addAll(node.getAllItems());
            }
        }
        return listItem;
    }

    public Map<Character, TrieNode<T>> getChildren() {
        return children;
    }
}
