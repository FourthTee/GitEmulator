package gitlet;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Commit object that stores information about the commit.
 * @author Fourth Teerakapibal
 */
public class Commit implements Serializable {

    /** Commit object constructor.
     *
     * @param message str
     * @param parentcommit str
     * @param files hm
     * @param branch str
     * @param mergeparent str
     * @param level int
     */
    Commit(String message, String parentcommit,
           HashMap<String, Blob> files, String branch,
           String mergeparent, int level) {
        _logmessage = message;
        _parent = parentcommit;
        _files = files;
        _branch = branch;
        ZonedDateTime now = ZonedDateTime.now();
        _time = now.format(DateTimeFormatter.ofPattern(
                "EEE MMM d HH:mm:ss yyyy xxxx"));
        _uid = genhashcode();
        _mergeparent = mergeparent;
        _level = level;

    }

    /** Initial commit when initialize.
     * @param message str
     * @return commit obj*/
    public static Commit initcom(String message) {
        Commit m = new Commit(message, "No parent",
                new HashMap<String, Blob>(), "master", "", 0);
        m._time = "Wed Dec 31 16:00:00 1969 -0800";
        return m;
    }

    /** Get files from the blob.
     * @param filename  str
     * @return Blob obj*/
    public Blob getfile(String filename) {
        return _files.get(filename);
    }

    /** Get files hashmap.
     * @return hashmap*/
    public HashMap<String, Blob> getallfiles() {
        return _files;
    }

    /** Check if file is in commit.
     *
     * @param filename str
     * @return boolean
     */
    public boolean containsfile(String filename) {
        return _files.containsKey(filename);
    }

    /** Generate hashcode of the object.
     * @return  String*/
    public String genhashcode() {
        List<Object> set = new ArrayList<>();
        set.add(_logmessage);
        set.add(_branch);
        set.add(_parent);
        set.add(_files.toString());
        return Utils.sha1(set);
    }

    /** Determine if commit is equal to another.
     *
     * @param c commit
     * @return bool
     */
    public boolean isequals(Commit c) {
        return this.getid().equals(c.getid());
    }

    /** Returns whether a file is in a commit.
     * @param filehashcode str
     * @return boolean*/
    public Boolean contains(String filehashcode) {
        return _files.containsKey(filehashcode);
    }

    /** Returns the log message of commit.*/
    public String getlog() {
        return _logmessage;
    }

    /** Returns the universal sha-1 id of the commit.*/
    public String getid() {
        return _uid;
    }

    /** Returns parent id.*/
    public String getparent() {
        return _parent;
    }

    /** Return time of commit.*/
    public String gettime() {
        return _time;
    }

    /** Return the merge parent.*/
    public String getmpar() {
        return _mergeparent;
    }

    /** Get level of commit.
     * @return int*/
    public int getlevel() {
        return _level;
    }


    /** Time that the commit was made.*/
    private String _time;

    /** branch of the commit.*/
    private String _branch;

    /** Files in the commit.*/
    private HashMap<String, Blob> _files;

    /** parent of the commit.*/
    private String _parent;

    /** Commit UID using the SHA-1 hashcode.*/
    private String _uid;

    /** Log message for commit.*/
    private String _logmessage;

    /** name of parent when merging.*/
    private String _mergeparent;

    /** Tree level of the commit.*/
    private int _level;


}
