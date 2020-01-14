package gitlet;

import java.io.Serializable;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/** Repository object that represents the gitlet repo.
 * @author Fourth Teerakapibal
 */

public class Repo implements Serializable {


    /** Initialize a repo object containing information about the gitlet repo.*/
    public Repo() {
        File repo = new File(".gitlet/repo/repofile");
        try {
            if (repo.exists()) {
                Repo prevrepo = Utils.readObject(repo, Repo.class);
                copy(prevrepo);
            } else {
                _head = null;
                _branch = "master";
                _currentcommit = null;
                _filenames = new HashMap<>();
                _filestrack = new HashMap<>();
                _branches = new HashMap<>();
                _commits = new HashSet<>();
                _staging = new HashMap<>();
                _removedfiles = new HashMap<>();
                _splitpoints = new HashMap<>();
                _nolongertracking = new HashMap<>();

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unexpected error");
            System.exit(0);
        }
    }

    /** Copies the content of repository into me.
     * @param repository repo*/
    private void copy(Repo repository) {
        _head = repository.gethead();
        _branch = repository.getbranch();
        _branches = repository.getbranches();
        _filestrack = repository._filestrack;
        _filenames = repository._filenames;
        _currentcommit = repository.getcommit();
        _commits = repository.getcommits();
        _staging = repository.getstaging();
        _removedfiles = repository._removedfiles;
        _splitpoints = repository._splitpoints;
        _nolongertracking = repository._nolongertracking;
    }

    /** Returns current head pointer of repo.*/
    public String gethead() {
        return _head;
    }

    /** Returns current branch of repo.*/
    public String getbranch() {
        return _branch;
    }

    /** Return all the branches of repo.*/
    public HashMap<String, String> getbranches() {
        return _branches;
    }

    /** Return current commit.*/
    public String getcommit() {
        return _currentcommit;
    }

    /** Return all the commits stored in the repo.*/
    public HashSet<String> getcommits() {
        return _commits;
    }

    /** Return the items in the staging area of the repo.*/
    public HashMap<String, Blob> getstaging() {
        return _staging;
    }

    /** Return collection of files tracked.
     * @return*/
    public HashMap<String, Blob> getfiles() {
        return _filestrack;
    }

    /** Return collection of file names mapping.*/
    public HashMap<String, String> getfilenames() {
        return _filenames;
    }

    /** Set current head pointer of repo.
     * @param head string.*/
    public void sethead(String head) {
        _head = head;
    }

    /** Set current branch of repo.
     * @param branch string.*/
    public void setbranch(String branch) {
        _branch = branch;
    }

    /** Set current commit of repo.
     * @param commit string.*/
    public void setcommit(String commit) {
        _currentcommit = commit;
    }

    /** Add branche to the branches of repo.
     * @param branchname branch string.
     * @param headcommit commit string.*/
    public void addbranch(String branchname, String headcommit) {
        _branches.put(branchname, headcommit);
    }

    /** get the commit at the head of a given branch.
     *
     * @param branchname str
     * @return String
     */
    public String getheadofbranch(String branchname) {
        return _branches.get(branchname);
    }

    /** contains a branch.
     * @param branchname str
     * @return boolean
     */
    public boolean hasbranch(String branchname) {
        return _branches.containsKey(branchname);
    }

    /** Reset the branch head.
     *
     * @param com obj
     */
    public void updatebranch(Commit com) {
        _branches.replace(_branch, com.getid());
    }

    /** Reset the branch head.
     *
     * @param comid str
     */
    public void updatebranch(String comid) {
        _branches.replace(_branch, comid);
    }

    /** Reset the branch head.
     * @param branch b
     * @param comid obj
     */
    public void updatebranch(String branch, String comid) {
        _branches.replace(branch, comid);
    }

    /** Add a commit to the commits stored in the repo.
     * @param commit commit object hash id.*/
    public void addcommit(String commit) {
        _commits.add(commit);
    }

    /** Add files to list of tracked files.
     * @param file blob obj.
     * @param filename string.*/
    public void addfiles(String filename, Blob file) {
        _filestrack.put(filename, file);
    }

    /** Get the file.
     * @param filename string
     * @return blob
     */
    public Blob gettrackfile(String filename) {
        return _filestrack.get(filename);
    }

    /** Add items in the staging area of the repo.
     * @param file blob obj.
     * @param filename string.*/
    public void addstaging(String filename, Blob file) {
        String hashcode = file.hashcode();
        _staging.put(filename, file);
    }

    /** Remove files from staging.
     * @param filename file blob.*/
    public void removestage(String filename) {
        _staging.remove(filename);
    }

    /** Remove files from list of tracked files.
     * @param file file blob.*/
    public void removefile(String file) {
        _filestrack.remove(file, _filestrack.get(file));
    }

    /** Return whether file is contained in the staging area.
     * @param filename file string.*/
    public boolean containstage(String filename) {
        return _staging.containsKey(filename);
    }

    /** Clears the staging area of the repo.*/
    public void clearstage() {
        _staging.clear();
    }

    /** Removes a branch.
     * @param branchname str*/
    public void deletebranch(String branchname) {
        _branches.remove(branchname);
    }

    /** Returns the full version of a 6 digit commit id.
     * @param commit str*/
    public String fullcommitid(String commit) {
        for (String s : getcommits()) {
            if (s.contains(commit)) {
                return s;
            }
        }
        return commit;
    }

    /** Retrieve split point of two branches.
     *
     * @param branch1 str
     * @param branch2 str
     * @return
     */
    public String getsplitpoint(String branch1, String branch2) {
        for (ArrayList<String> s : _splitpoints.keySet()) {
            if (s.contains(branch1) && s.contains(branch2)) {
                return _splitpoints.get(s);
            }
        }
        System.out.println("Error split point");
        System.exit(0);
        return null;
    }

    /** Add a splitpoint.
     * @param branchto str
     */
    public void addsplitpoint(String branchto) {
        if (_splitpoints.size() != 1) {
            for (ArrayList<String> split : _splitpoints.keySet()) {
                if (split.contains(_branch)) {
                    for (String branch : split) {
                        if (!branch.equals(_branch)) {
                            ArrayList<String> b = new ArrayList<>();
                            b.add(branch);
                            b.add(branchto);
                            _splitpoints.put(b, _splitpoints.get(split));
                        }
                    }
                }
            }
        }

        ArrayList<String> s = new ArrayList<>();
        s.add(_branch);
        s.add(branchto);
        _splitpoints.put(s, getcommit());

    }

    /** Add file to removedfiles lst.
     * @param file blob*/
    public void addremovefile(Blob file) {
        _removedfiles.put(file.name(), file);
    }

    /** Return the hashmap of removed files.*/
    public HashMap<String, Blob> getremovedfiles() {
        return _removedfiles;
    }

    /** Clear removedfiles.*/
    public void clearremovedfiles() {
        _removedfiles.clear();
    }

    /** Whether file is contained in removal list.
     * @param filename filename
     * @return boolean*/
    public boolean containinremoval(String filename) {
        return _removedfiles.containsKey(filename);
    }

    /** Delete file in removal.
     * @param filename str*/
    public void deleteremoval(String filename) {
        _removedfiles.remove(filename, _removedfiles.get(filename));
    }

    /** add to nolongertrack group.
     *
     * @param file blob
     */
    public void addnolongertracking(Blob file) {
        _nolongertracking.put(file.name(), file);
    }

    /** delete from nolonger track.
     *
     * @param filename str
     */
    public void deletenolongertracking(String filename) {
        _nolongertracking.remove(filename, _nolongertracking.get(filename));
    }

    /** get all nontracked files.
     *
     * @return Hashmap
     */
    public HashMap<String, Blob> getnolongertracking() {
        return _nolongertracking;
    }

    /** check if a file is no long track.
     * @param filename str
     * @return boolean
     */
    public boolean isnolongertracking(String filename) {
        return _nolongertracking.containsKey(filename);
    }

    /** reset the tracked files.
     *
     * @param s hashmap
     */
    public void setfiles(HashMap<String, Blob> s) {
        _filestrack = s;
    }

    /** return if file is tracked.
     *
     * @param filename str
     * @return bool
     */
    public boolean containstrackfile(String filename) {
        return _filestrack.containsKey(filename);
    }

    /** Head of the current commit. */
    private String _head;

    /** Branch of the current repo. */
    private String _branch;

    /** Current commit id.*/
    private String _currentcommit;

    /** Collection of all the branches in
     * the repo with (branch name, head commit).*/
    private HashMap<String, String> _branches;

    /** Collection of all the commits in the repo.*/
    private HashSet<String> _commits;

    /** Collection of items in the staging area of the repo.*/
    private HashMap<String, Blob> _staging;

    /** Collection of files that are tracked.*/
    private HashMap<String, Blob> _filestrack;

    /** Mapping from file name to hashcodes.*/
    private HashMap<String, String> _filenames;

    /** Map from filename to Blob of removed files.*/
    private HashMap<String, Blob> _removedfiles;

    /** Map of all the splitspoints.*/
    private HashMap<ArrayList<String>, String> _splitpoints;

    /** Map of all nolongertracking.*/
    private HashMap<String, Blob> _nolongertracking;


}
