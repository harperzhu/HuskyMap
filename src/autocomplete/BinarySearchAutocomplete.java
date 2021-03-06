package autocomplete;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> terms;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        for (CharSequence element: terms){
            this.terms.add(element);
        }
        Collections.sort(this.terms, CharSequence::compare);
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        int x;
        List<CharSequence> result = new ArrayList<>();
        if (prefix == null || prefix.length() == 0) {
            return result;
        }
        int i = Collections.binarySearch(terms, prefix, CharSequence::compare);
        if (i >= 0) {
             x = i;
        } else {
             x = -i -1;
        }
        
        CharSequence part = "";
        
        if (prefix.length() <= part.length()) {
            part = terms.get(x).subSequence(0, prefix.length());
            while (CharSequence.compare(prefix, part) == 0){
                result.add(terms.get(x));
                x = x + 1;
                part = terms.get(x).subSequence(0, prefix.length());
            }
        }
    return result;
    }
}
