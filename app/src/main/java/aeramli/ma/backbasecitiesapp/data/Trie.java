package aeramli.ma.backbasecitiesapp.data;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

public class Trie<T extends TrieItem> {
    private TrieNode<T> root;

    public Trie() {
        root = new TrieNode<>();
    }

    public void add(@NonNull List<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    public void add(@NonNull T item) {
        add(item.getValue(), item, root);
    }

    private void add(@NonNull String value, @NonNull T item, TrieNode<T> node) {
        if (value.isEmpty()) {
            node.markAsEnd(item);
            return;
        }
        Character c = value.charAt(0);
        TrieNode<T> child;
        if (!node.getChildren().containsKey(c)) {
            child = new TrieNode<>();
            node.getChildren().put(c, child);
        } else {
            child = node.getChildren().get(c);
        }
        add(value.substring(1), item, child);
    }

    public List<T> getItems() {
        return root.getAllItems();
    }

    public List<T> autocomplete(@NonNull String prefix) {
        final String formattedPrefix = prefix.toLowerCase();
        TrieNode<T> node = root;
        char letter;
        for (int i = 0; i < formattedPrefix.length(); i++) {
            letter = formattedPrefix.charAt(i);
            if (node.getChildren().containsKey(letter)) {
                node = node.getChildren().get(letter);
            } else {
                return Collections.emptyList();
            }
        }
        return node.getAllItems();
    }
}
