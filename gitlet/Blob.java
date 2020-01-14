package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Blob object containing information about the file.
 * @author Fourth Teerakapibal
 */
public class Blob implements Serializable {

    /** Blob constructor that takes in a file object.
     * @param file file that the blob represents.
     * */
    public Blob(File file) {
        _filename = file.getName();
        _content = Utils.readContentsAsString(file);
        _hashcode = genhashcode();
    }

    /** Generate hashcode of the object.
     * @return hashcode of the Blob object SHA-1.
     * */
    private String genhashcode() {
        List<Object> set = new ArrayList<>();
        set.add(name());
        set.add(content());
        return Utils.sha1(set);
    }

    /** Check if a blob is equivalent to another one.
     * @param blob takes in another blob.
     * @return whether the blob is equivalent.
     * */
    public boolean equals(Blob blob) {
        return this.hashcode().equals(blob.hashcode());
    }

    /** Checks if a blob is of the same file.
     *  @param blob takes in another blob.
     *  @return whether the blob is equivalent file name.
     *  */
    public boolean samefilename(Blob blob) {
        return this.name().equals(blob.name());
    }

    /** Return file name.*/
    public String name() {
        return _filename;
    }

    /** Return the SHA-1 hashcode of object.*/
    public String hashcode() {
        return _hashcode;
    }

    /** Return content of the file.*/
    public String content() {
        return _content;
    }

    /** Name of the file (Directory).*/
    private String _filename;

    /** SHA-1 hashcode of blob object.*/
    private String _hashcode;

    /** Content of file read a string of bytes.*/
    private String _content;
}
