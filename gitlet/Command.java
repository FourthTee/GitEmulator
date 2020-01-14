package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;

/** Process the command given by the User.
 * @author Fourth Teerakapibal*/


public class Command {


    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    static void process(String[] command) {
        _repo = new Repo();
        switch (command[0]) {
        case "init":
            initgit();
            return;
        case "add":
            checkgitrepo();
            processadd(command);
            return;
        case "commit":
            checkgitrepo();
            processcommit(command);
            return;
        case "rm":
            checkgitrepo();
            processremove(command);
            writerepo();
            return;
        case "log":
            checkgitrepo();
            processlog(command);
            return;
        case "global-log":
            checkgitrepo();
            processglog(command);
            return;
        case "find":
            checkgitrepo();
            processfind(command);
            return;
        case "status":
            checkgitrepo();
            processstatus(command);
            return;
        case "checkout":
            checkgitrepo();
            processcheckout(command);
            return;

        case "branch":
            checkgitrepo();
            processbranch(command);
            return;
        case "rm-branch":
            checkgitrepo();
            processrmbranch(command);
            return;
        case "reset":
            checkgitrepo();
            processreset(command);
            return;
        case "merge":
            checkgitrepo();
            processmerge(command);
            return;
        default:
            Reporterr.commandnotexist();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processlog(String[] command) {
        try {
            String check = command[1];
            Reporterr.incorrectoper();
        } catch (IndexOutOfBoundsException e) {
            log();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processadd(String[] command) {
        try {
            String filename = command[1];
            add(filename);
            writerepo();
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processcommit(String[] command) {
        try {
            if (command.length == 1 || command[1].equals("")) {
                Utils.message("Please enter a commit message.");
            } else if (command.length == 2) {
                String message = command[1];
                commit(message);
            } else {
                throw new IndexOutOfBoundsException("Too many args");
            }

        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processremove(String[] command) {
        try {
            String filename = command[1];
            remove(filename);
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processmerge(String[] command) {
        try {
            if (command.length != 2) {
                throw new IndexOutOfBoundsException("Wrong # of Args.");
            } else {
                String branchname = command[1];
                merge(branchname);
            }
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processreset(String[] command) {
        try {
            if (command.length != 2) {
                throw new IndexOutOfBoundsException("Wrong # of Args.");
            } else {
                String commitid = command[1];
                reset(commitid);
            }
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processrmbranch(String[] command) {
        try {
            if (command.length != 2) {
                throw new IndexOutOfBoundsException("Wrong # of Args.");
            } else {
                String branchname = command[1];
                removebranch(branchname);
            }
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processbranch(String[] command) {
        try {
            if (command.length != 2) {
                throw new IndexOutOfBoundsException("Wrong # of Args.");
            } else {
                String branchname = command[1];
                branch(branchname);
            }
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processcheckout(String[] command) {
        try {
            if (command.length == 2) {
                String branchname = command[1];
                checkoutbranch(branchname);
            } else if (command[1].equals("--") && command.length == 3) {
                String filename = command[2];
                checkout(_repo.getcommit(), filename);
            } else if (command[2].equals("--") && command.length == 4) {
                String commitid = command[1];
                String filename = command[3];
                checkout(commitid, filename);
            } else {
                throw new IndexOutOfBoundsException("Error");
            }
            writerepo();
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processstatus(String[] command) {
        try {
            if (command.length != 1) {
                throw new IndexOutOfBoundsException("Wrong # of args");
            } else {
                status();
            }
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processfind(String[] command) {
        try {
            String message = command[1];
            findcommit(message);
        } catch (IndexOutOfBoundsException e) {
            Reporterr.incorrectoper();
        }
    }

    /** Takes in a array of strings with the commands and
     * calls the runs the designated process.
     * @param command array of strings*/
    private static void processglog(String[] command) {
        try {
            String check = command[1];
            Reporterr.incorrectoper();
        } catch (IndexOutOfBoundsException e) {
            globallog();
        }
    }


    /** Initialize a git on the directory. **/
    private static void initgit() {
        _gitlet = new File(".gitlet");
        if (_gitlet.exists()) {
            Reporterr.gitexists();
        } else {
            File commits = new File(".gitlet/commits");
            File staging = new File(".gitlet/staging");
            File repo = new File(".gitlet/repo");
            _gitlet.mkdir();
            commits.mkdir();
            staging.mkdir();
            repo.mkdir();
            initcommit("initial commit");
            writerepo();
        }
    }

    /** Perform the add command by adding untracked files to the staging area.
     * @param filename filename*/
    private static void add(String filename) {
        File file = new File(filename);

        if (file.exists()) {
            Blob fileblob = new Blob(file);

            String comid = _repo.getcommit();
            File comfile = new File(".gitlet/commits/" + comid);
            Commit com = Utils.readObject(comfile, Commit.class);
            if (_repo.containinremoval(filename)) {
                _repo.deleteremoval(filename);
                _repo.addfiles(filename, fileblob);
            }
            if (_repo.isnolongertracking(filename)) {
                _repo.deletenolongertracking(filename);
                _repo.addfiles(filename, fileblob);
            }
            if (com.containsfile(filename)) {
                Blob oldfile = com.getfile(filename);
                if (oldfile.equals(fileblob)) {
                    File stagefile = new File(
                            ".gitlet/staging/" + fileblob.hashcode());
                    if (_repo.getstaging().containsKey(filename)
                            && stagefile.exists()) {
                        _repo.removestage(filename);
                        stagefile.delete();
                    }
                    return;
                }
            }
            File stagefile = new File(".gitlet/staging/" + fileblob.hashcode());
            _repo.addstaging(filename, fileblob);
            _repo.addfiles(filename, fileblob);
            Utils.writeContents(stagefile, Utils.serialize(fileblob));

        } else {
            Reporterr.filenotexist();
        }
    }

    /** Check if directory is a gitlet repo. Throws error if not.*/
    private static void checkgitrepo() {
        _gitlet = new File(".gitlet");
        if (!_gitlet.exists()) {
            Reporterr.notindir();
        }
    }

    /** Write the repo object into the repo folder inside
     * .gitlet directory for uses in next command.*/
    private static void writerepo() {
        File repofile = new File(".gitlet/repo/repofile");
        Utils.writeObject(repofile, _repo);
    }

    /** Add files from staging to list of tracked files.*/
    private static void precommit() {
        HashMap<String, Blob> staging = _repo.getstaging();
        HashMap<String, Blob> files = _repo.getfiles();
        if (staging.isEmpty() && _repo.getremovedfiles().isEmpty()) {
            Reporterr.nofilestocommit();
        }
        for (String filestage: staging.keySet()) {
            boolean replace = false;
            for (String file: files.keySet()) {
                if (staging.get(filestage).samefilename(files.get(file))) {
                    files.remove(file, files.get(file));
                    files.put(filestage, staging.get(filestage));
                    replace = true;
                    break;
                }
            }
            if (!replace) {
                _repo.addfiles(filestage, staging.get(filestage));
            }
        }
    }

    /** Create and Store commit object.
     * @param message stringa*/
    private static void commit(String message) {
        precommit();
        try {
            File f = new File(".gitlet/commits/" + _repo.getcommit());
            Commit prevcom = Utils.readObject(f, Commit.class);
            Commit com = new Commit(
                    message, _repo.getcommit(),
                    _repo.getfiles(), _repo.getbranch(),
                    "", prevcom.getlevel() + 1);
            _repo.addcommit(com.getid());
            _repo.sethead(com.getid());
            _repo.setcommit(com.getid());
            _repo.updatebranch(com);
            File comfile = new File(".gitlet/commits/" + com.getid());
            Utils.writeObject(comfile, com);
            File stage = new File(".gitlet/staging");
            deletedir(stage);
            stage.mkdir();
            _repo.clearstage();
            _repo.clearremovedfiles();
            writerepo();
        } catch (IOException e) {
            Utils.message("Unexpected err");
        }
    }

    /** Deletes a directory.
     * @param file file*/
    private static void deletedir(File file) throws IOException {
        if (file.isDirectory()) {
            File[] filelst = file.listFiles();
            if (filelst != null) {
                for (File f : filelst) {
                    f.delete();
                }
            }
        }
        if (!file.delete()) {
            throw new IOException("Cannot delete directory: " + file);
        }
    }

    /** Run the initial commit made when initializing the gitlet repo.
     * @param message message*/
    private static void initcommit(String message) {
        Commit com = Commit.initcom(message);
        _repo.addcommit(com.getid());
        _repo.sethead(com.getid());
        _repo.setcommit(com.getid());
        _repo.addbranch(_repo.getbranch(), com.getid());
        File comfile = new File(".gitlet/commits/" + com.getid());
        Utils.writeObject(comfile, com);
        _repo.clearstage();
    }


    /** Checkout to a file to a specific commit id.
     * @param filename filename
     * @param commitid commit idS*/
    private static void checkout(String commitid, String filename) {
        checkuntrack(commitid);
        commitid = _repo.fullcommitid(commitid);
        File comfile = new File(".gitlet/commits/" + commitid);
        if (!comfile.exists()) {
            Reporterr.nocommitid();
        }
        Commit commit = Utils.readObject(comfile, Commit.class);
        if (commit.containsfile(filename)) {
            Blob file = commit.getfile(filename);
            File newfile = new File(filename);
            Utils.writeContents(newfile, file.content());
        } else {
            Reporterr.nofileincommit();
        }
    }

    /** Find commits with a specific message.
     * @param message string*/
    private static void findcommit(String message) {
        HashSet<String> commits = _repo.getcommits();
        int count = 0;
        for (String s: commits) {
            File comfile = new File(".gitlet/commits/" + s);
            Commit commit = Utils.readObject(comfile, Commit.class);
            if (message.equals(commit.getlog())) {
                Utils.message(s);
                count++;
            }
        }
        if (count == 0) {
            Reporterr.commitnotfound();
        }
    }

    /** Remove file.
     * @param filename filename*/
    private static void remove(String filename) {

        File file = new File(filename);
        try {
            Blob fileblob = _repo.gettrackfile(filename);
            String curcomid = _repo.getcommit();
            File comfile = new File(".gitlet/commits/" + curcomid);
            Commit curcommit = Utils.readObject(comfile, Commit.class);
            if (file.exists() && curcommit.containsfile(filename)) {
                file.delete();
            }
            if (_repo.containstage(filename)
                    || curcommit.containsfile(filename)) {
                if (_repo.containstage(filename)) {
                    Blob b = _repo.getstaging().get(filename);
                    File stagefile = new File(
                            ".gitlet/staging/"
                                    + b.hashcode());
                    if (stagefile.exists()) {
                        stagefile.delete();
                    }
                    _repo.removestage(filename);
                }
                if (curcommit.containsfile(filename)) {
                    _repo.addremovefile(fileblob);
                    _repo.addnolongertracking(fileblob);
                    _repo.removefile(filename);
                }
            } else {
                throw new IndexOutOfBoundsException("No reason to remove");
            }

        } catch (IndexOutOfBoundsException e) {
            Reporterr.noremove();
        }
    }

    /** Returns the commit logs.*/
    private static void log() {
        ArrayList<Commit> commitlst = new ArrayList<>();
        String comid = _repo.getcommit();
        File comfile = new File(".gitlet/commits/" + comid);
        Commit com = Utils.readObject(comfile, Commit.class);
        if (com.getparent().equals("No parent")) {
            commitlst.add(com);
        } else {
            while (!com.getparent().equals("No parent")) {
                commitlst.add(com);
                comfile = new File(".gitlet/commits/" + com.getparent());
                com = Utils.readObject(comfile, Commit.class);
            }
            commitlst.add(com);
        }
        for (Commit s : commitlst) {
            System.out.println("===");
            System.out.println("commit " + s.getid());
            System.out.println("Date: " + s.gettime());
            System.out.println(s.getlog());
            System.out.println();
        }
    }

    /** Returns the commit logs.*/
    private static void globallog() {
        ArrayList<Commit> commitlst = new ArrayList<>();
        for (String commit: _repo.getcommits()) {
            File comfile = new File(".gitlet/commits/" + commit);
            Commit com = Utils.readObject(comfile, Commit.class);
            commitlst.add(com);
        }
        for (Commit s : commitlst) {
            System.out.println("===");
            System.out.println("commit " + s.getid());
            System.out.println("Date: " + s.gettime());
            System.out.println(s.getlog());
            System.out.println();
        }
    }



    /** Creates a new branch in gitlet.
     * @param branchname stringr*/
    private static void branch(String branchname) {
        if (_repo.hasbranch(branchname)) {
            Reporterr.branchalreadyexist();
        } else {
            _repo.addbranch(branchname, _repo.getcommit());
            _repo.addsplitpoint(branchname);
            writerepo();
        }
    }

    /** Checkout to a specific branch.
     * @param branchname string*/
    private static void checkoutbranch(String branchname) {
        checkuntrack(_repo.getheadofbranch(branchname));
        if (!_repo.hasbranch(branchname)) {
            Reporterr.branchdne();
        } else if (_repo.getbranch().equals(branchname)) {
            Reporterr.alreadyonbranch();
        } else {
            _repo.setbranch(branchname);
            _repo.setcommit(_repo.getheadofbranch(branchname));
            _repo.sethead(_repo.getheadofbranch(branchname));
            File headcommit = new File(".gitlet/commits/"
                    + _repo.getheadofbranch(branchname));
            Commit commit = Utils.readObject(headcommit, Commit.class);
            HashMap<String, Blob> allfiles = _repo.getfiles();
            for (String filename : commit.getallfiles().keySet()) {
                Blob oldfile = commit.getfile(filename);
                File newfile = new File(filename);
                Utils.writeContents(newfile, oldfile.content());
            }
            for (String filename : allfiles.keySet()) {
                if (!commit.containsfile(filename)) {
                    File newfile = new File(filename);
                    newfile.delete();
                }
            }
            for (String filename : _repo.getnolongertracking().keySet()) {
                if (commit.containsfile(filename)) {
                    Blob oldfile = commit.getfile(filename);
                    File newfile = new File(filename);
                    Utils.writeContents(newfile, oldfile.content());
                }
            }
            _repo.setfiles(commit.getallfiles());
            writerepo();
        }
    }

    /** Removes the branch.
     * @param branchname string*/
    private static void removebranch(String branchname) {
        if (!_repo.hasbranch(branchname)) {
            Reporterr.branchdne2();
        } else if (_repo.getbranch().equals(branchname)) {
            Reporterr.alreadyonbranch2();
        } else {
            _repo.deletebranch(branchname);
        }
        writerepo();
    }

    /** Reset the commit to the one listed.
     * @param commitid string*/
    private static void reset(String commitid) {
        checkuntrack(commitid);
        if (commitid.length() == 6) {
            commitid = _repo.fullcommitid(commitid);
        }
        File comfile = new File(".gitlet/commits/" + commitid);
        if (!comfile.exists()) {
            Reporterr.nocommitid();
        }
        Commit commit = Utils.readObject(comfile, Commit.class);
        _repo.sethead(commitid);
        _repo.setcommit(commitid);
        try {
            File stage = new File(".gitlet/staging");
            deletedir(stage);
            stage.mkdir();
        } catch (IOException e) {
            Utils.message("Unexpected reset err");
            System.exit(0);
        }

        HashMap<String, Blob> allfiles = _repo.getfiles();
        for (String filename : commit.getallfiles().keySet()) {
            Blob oldfile = commit.getfile(filename);
            File newfile = new File(filename);
            Utils.writeContents(newfile, oldfile.content());
        }
        for (String filename : allfiles.keySet()) {
            if (!commit.containsfile(filename)) {
                if (filename.equals("m.txt")) {
                    System.out.println("hi");
                }
                File newfile = new File(filename);
                newfile.delete();
            }
        }
        for (String filename : _repo.getnolongertracking().keySet()) {
            if (commit.containsfile(filename)) {
                Blob oldfile = commit.getfile(filename);
                File newfile = new File(filename);
                Utils.writeContents(newfile, oldfile.content());
            }
        }
        _repo.setfiles(commit.getallfiles());
        _repo.updatebranch(commit);
        _repo.clearstage();
        writerepo();
    }

    /** Searches for untrack file.
     * @param commitid string
     */
    private static void checkuntrack(String commitid) {
        File directory = new File(System.getProperty("user.dir"));
        for (File file : directory.listFiles()) {
            boolean containsfilestage = _repo.containstage(file.getName());
            if (!file.getName().equals(".gitlet")
                    && !containsfilestage
                    && !_repo.containstrackfile(file.getName())
                    && !_repo.containinremoval(file.getName())
                    && !_repo.isnolongertracking(file.getName())) {
                Reporterr.untrack();
            } else {
                continue;
            }
        }
    }

    /** Check if a file is removed.*/
    private static void checkremoved() {
        File headcommit = new File(".gitlet/commits/" + _repo.getcommit());
        Commit commit = Utils.readObject(headcommit, Commit.class);
        for (String filename : commit.getallfiles().keySet()) {
            File f = new File(filename);
            if (!f.exists()) {
                _repo.addremovefile(commit.getfile(filename));
            }
        }
    }

    /** Status command.*/
    private static void status() {
        System.out.println("=== Branches ===");
        Set<String> branches = new TreeSet<String>(
                _repo.getbranches().keySet());
        Set<String> staging = new TreeSet<String>(_repo.getstaging().keySet());
        Set<String> remove = new TreeSet<String>(
                _repo.getremovedfiles().keySet());
        for (String filenames : branches) {
            if (filenames.equals(_repo.getbranch())) {
                System.out.println("*" + filenames);
            } else {
                System.out.println(filenames);
            }
        }
        System.out.println();
        System.out.println("=== Staged Files ===");
        for (String filenames : staging) {
            System.out.println(filenames);
        }
        System.out.println();
        System.out.println("=== Removed Files ===");
        for (String filenames : remove) {
            System.out.println(filenames);
        }
        System.out.println();
        System.out.println("=== Modifications Not Staged For Commit ===");
        ArrayList<Blob> modified = getmodified(_repo.getcommit());
        for (Blob f : modified) {
            System.out.println(f.name() + " (modified)");
        }
        ArrayList<File> deleted = getdeleted(_repo.getcommit());
        for (File f : deleted) {
            System.out.println(f.getName() + " (deleted)");
        }
        System.out.println();
        System.out.println("=== Untracked Files ===");
        ArrayList<File> untrack = getuntrack();
        for (File f : untrack) {
            System.out.println(f.getName());
        }
        System.out.println();
    }

    /** Searches for untrack file.
     * @return arraylst*/
    private static ArrayList<File> getuntrack() {
        ArrayList<File> untrack = new ArrayList<>();
        File directory = new File(System.getProperty("user.dir"));
        for (File file : directory.listFiles()) {
            boolean containsfilestage = _repo.containstage(file.getName());
            if (!file.getName().equals(".gitlet")
                    && !containsfilestage
                    && !_repo.containstrackfile(file.getName())
                    && !_repo.containinremoval(file.getName())
                    && !_repo.isnolongertracking(file.getName())) {
                untrack.add(file);
            } else {
                continue;
            }
        }
        return untrack;
    }

    /** Get the list of modified files.
     *
     * @param commitid str
     * @return arraylst
     */
    private static ArrayList<Blob> getmodified(String commitid) {
        ArrayList<Blob> modified = new ArrayList<>();
        File com = new File(".gitlet/commits/" + commitid);
        Commit commit = Utils.readObject(com, Commit.class);
        HashMap<String, Blob> files = commit.getallfiles();
        for (String filename : files.keySet()) {
            File wd = new File(filename);
            if (wd.exists()) {
                Blob wdblob = new Blob(wd);
                if (!wdblob.equals(commit.getfile(filename))) {
                    modified.add(wdblob);
                }
            }
        }
        return modified;
    }

    /** Get the list of deleted files.
     *
     * @param commitid str
     * @return arraylst
     */
    private static ArrayList<File> getdeleted(String commitid) {
        ArrayList<File> deleted = new ArrayList<>();
        File com = new File(".gitlet/commits/" + commitid);
        Commit commit = Utils.readObject(com, Commit.class);
        HashMap<String, Blob> files = commit.getallfiles();
        for (String filename : files.keySet()) {
            File wd = new File(filename);
            if (!wd.exists() && !_repo.containinremoval(filename)) {
                deleted.add(wd);
            }
        }
        return deleted;
    }

    /** Merges two branches together withe the given conditions.
     * @param branchname string*/
    private static void merge(String branchname) {
        checkuntrack(_repo.getcommit());
        if (!_repo.getstaging().isEmpty()) {
            Reporterr.uncommitedchanges();
        }
        if (branchname.equals(_repo.getbranch())) {
            Reporterr.selfmerge();
        }
        if (!_repo.hasbranch(branchname)) {
            Reporterr.branchdne2();
        }
        if (checksplitpoint(_repo.getbranch(), branchname)) {
            String mybranch = _repo.getbranch();
            checkoutbranch(branchname);
            _repo.setbranch(mybranch);
            _repo.updatebranch(_repo.getheadofbranch(branchname));
            _repo.setcommit(_repo.getheadofbranch(branchname));
            _repo.sethead(_repo.getheadofbranch(branchname));
            Utils.message("Current branch fast-forwarded.");
            return;
        } else if (checksplitpoint(branchname, _repo.getbranch())) {
            Utils.message("Given branch is an ancestor of the current branch.");
            return;
        } else {
            String splitpoint = getsplitpoint(
                    _repo.getcommit(), _repo.getheadofbranch(branchname));
            File sp = new File(".gitlet/commits/"
                    + splitpoint);
            File gb = new File(".gitlet/commits/"
                    + _repo.getheadofbranch(branchname));
            File cb = new File(".gitlet/commits/"
                    + _repo.getcommit());
            Commit spcom = Utils.readObject(sp, Commit.class);
            Commit gbcom = Utils.readObject(gb, Commit.class);
            Commit cbcom = Utils.readObject(cb, Commit.class);

            mergehelp1(spcom, cbcom, gbcom);
            mergehelp2(spcom, cbcom, gbcom);
            for (String f : cbcom.getallfiles().keySet()) {
                if (spcom.containsfile(f)
                        && !gbcom.containsfile(f)
                        && !cbcom.getfile(f).equals(spcom.getfile(f))) {
                    String contents = "<<<<<<< HEAD\n";
                    contents += cbcom.getfile(f).content();
                    contents += "=======\n";
                    contents += "" + ">>>>>>>";
                    Utils.writeContents(new File(f), contents);
                    _repo.addfiles(f, new Blob(new File(f)));
                    Utils.message("Encountered a merge conflict.");
                }
            }
            mergecommit("Merged "
                    + branchname + " into "
                    + _repo.getbranch() + ".", branchname, cbcom, gbcom);
        }
        writerepo();
    }

    /** Help perform merge action 1 step.
     * @param spcom obj
     * @param cbcom obj
     * @param gbcom obj
     */
    private static void mergehelp1(Commit spcom, Commit cbcom, Commit gbcom) {
        for (String f: spcom.getallfiles().keySet()) {
            if (gbcom.containsfile(f)
                    && cbcom.containsfile(f)) {
                if (!gbcom.getfile(f).equals(spcom.getfile(f))
                        && cbcom.getfile(f).equals(spcom.getfile(f))) {
                    _repo.addstaging(f, gbcom.getfile(f));
                    Blob fileblob = gbcom.getfile(f);
                    Utils.writeObject(new File(".gitlet/staging/"
                            + fileblob.hashcode()), fileblob);
                    File target = new File(f);
                    Utils.writeContents(target, gbcom.getfile(f).content());
                    continue;
                } else if (gbcom.getfile(f).equals(spcom.getfile(f))
                        && !cbcom.getfile(f).equals(spcom.getfile(f))) {
                    continue;
                } else if (gbcom.getfile(f).equals(spcom.getfile(f))
                        && cbcom.getfile(f).equals(spcom.getfile(f))) {
                    continue;
                }
            } else if (!gbcom.containsfile(f) && !cbcom.containsfile(f)) {
                continue;
            } else if (!gbcom.containsfile(f) && cbcom.containsfile(f)) {
                if (cbcom.getfile(f).equals(spcom.getfile(f))) {
                    File file = new File(f);
                    file.delete();
                    _repo.removefile(f);
                    _repo.addnolongertracking(cbcom.getfile(f));
                    continue;
                }
            } else if (gbcom.containsfile(f) && !cbcom.containsfile(f)) {
                continue;
            }
        }
    }

    /** Help perform merge action 1 step.
     * @param spcom obj
     * @param cbcom obj
     * @param gbcom obj
     */
    private static void mergehelp2(Commit spcom, Commit cbcom, Commit gbcom) {
        Boolean conflict = false;
        for (String f : gbcom.getallfiles().keySet()) {
            if (!spcom.containsfile(f) && cbcom.containsfile(f)) {
                _repo.addfiles(f, gbcom.getfile(f));
                Blob fileblob = gbcom.getfile(f);
                Utils.writeObject(new File(".gitlet/staging/"
                        + fileblob.hashcode()), fileblob);
                File target = new File(f);
                Utils.writeContents(target, gbcom.getfile(f).content());
            } else if (cbcom.containsfile(f)
                    && !gbcom.getfile(f).equals(cbcom.getfile(f))) {
                String contents = "<<<<<<< HEAD\n";
                contents += cbcom.getfile(f).content();
                contents += "=======\n";
                contents += gbcom.getfile(f).content() + ">>>>>>>";
                Utils.writeContents(new File(f), contents);
                _repo.addfiles(f, new Blob(new File(f)));
                Utils.message("Encountered a merge conflict.");
            } else if (!spcom.containsfile(f) && !cbcom.containsfile(f)) {
                _repo.addfiles(f, gbcom.getfile(f));
                File target = new File(f);
                Utils.writeContents(target, gbcom.getfile(f).content());
            } else if (!cbcom.containsfile(f)
                    && !gbcom.getfile(f).equals(spcom.getfile(f))) {
                String contents = "<<<<<<< HEAD\n";
                contents += "";
                contents += "=======\n";
                contents += gbcom.getfile(f).content() + ">>>>>>>";
                Utils.writeContents(new File(f), contents);
                _repo.addfiles(f, new Blob(new File(f)));
                Utils.message("Encountered a merge conflict.");
            }
        }
    }

    /** Check if a branch is a split point of another branch.
     * @param branch b
     * @param splitbranch sp
     * @return boolean*/
    private static boolean checksplitpoint(String splitbranch, String branch) {
        File comsplitfile = new File(".gitlet/commits/"
                + _repo.getheadofbranch(splitbranch));
        File combranchfile = new File(".gitlet/commits/"
                + _repo.getheadofbranch(branch));
        Commit splitcommit = Utils.readObject(comsplitfile, Commit.class);
        Commit branchcommit = Utils.readObject(combranchfile, Commit.class);
        if (branchcommit.getid().equals(splitcommit.getid())) {
            return true;
        } else {
            while (!branchcommit.getid().equals(splitcommit.getid())
                    && !branchcommit.getparent().equals("No parent")) {
                combranchfile = new File(".gitlet/commits/"
                        + branchcommit.getparent());
                branchcommit = Utils.readObject(combranchfile, Commit.class);
                if (branchcommit.getid().equals(splitcommit.getid())) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns the commit at the splitpoint.
     *
     * @param b1 str
     * @param b2 str
     * @return
     */
    private static String getsplitpoint(String b1, String b2) {
        File comsplitfile = new File(".gitlet/commits/"
                + b1);
        File combranchfile = new File(".gitlet/commits/"
                + b2);
        Commit b1c = Utils.readObject(comsplitfile, Commit.class);
        Commit b2c = Utils.readObject(combranchfile, Commit.class);

        if (b1c.isequals(b2c)) {
            return b1c.getid();
        } else if (b1c.getparent().equals("No parent")
                || b2c.getparent().equals("No parent")) {
            if (b1c.getparent().equals("No parent")) {
                return getsplitpoint(b1c.getid(), b2c.getparent());
            } else {
                return getsplitpoint(b1c.getparent(), b2c.getid());
            }
        } else if (b1c.getmpar().equals("")
                && b2c.getmpar().equals("")) {
            return getsplitpoint(b1c.getparent(), b2c.getparent());
        } else if (!b1c.getmpar().equals("")
                && b2c.getmpar().equals("")) {
            String commit1 = getsplitpoint(b1c.getparent(), b2c.getparent());
            String commit2 = getsplitpoint(b1c.getmpar(), b2c.getparent());
            File com1 = new File(".gitlet/commits/" + commit1);
            File com2 = new File(".gitlet/commits/" + commit2);
            Commit c1commit = Utils.readObject(com1, Commit.class);
            Commit c2commit = Utils.readObject(com2, Commit.class);
            if (c1commit.getlevel() > c2commit.getlevel()) {
                return c1commit.getid();
            } else {
                return c2commit.getid();
            }
        } else if (b1c.getmpar().equals("") && !b2c.getmpar().equals("")) {
            String commit1 = getsplitpoint(b1c.getparent(), b2c.getparent());
            String commit2 = getsplitpoint(b1c.getparent(), b2c.getmpar());
            File com1 = new File(".gitlet/commits/" + commit1);
            File com2 = new File(".gitlet/commits/" + commit2);
            Commit c1commit = Utils.readObject(com1, Commit.class);
            Commit c2commit = Utils.readObject(com2, Commit.class);
            if (c1commit.getlevel() > c2commit.getlevel()) {
                return c1commit.getid();
            } else {
                return c2commit.getid();
            }
        } else if (!b1c.getmpar().equals("") && !b2c.getmpar().equals("")) {
            String commit1 = getsplitpoint(b1c.getparent(), b2c.getparent());
            String commit2 = getsplitpoint(b1c.getparent(), b2c.getmpar());
            String commit3 = getsplitpoint(b1c.getparent(), b2c.getparent());
            String commit4 = getsplitpoint(b1c.getparent(), b2c.getmpar());
            File com1 = new File(".gitlet/commits/" + commit1);
            File com2 = new File(".gitlet/commits/" + commit2);
            File com3 = new File(".gitlet/commits/" + commit3);
            File com4 = new File(".gitlet/commits/" + commit4);
            return maxdepth(com1, com2, com3, com4);
        }
        Utils.message("cant find split");
        return null;
    }

    /** Return maxdepth.
     *
     * @param com1 commit files
     * @param com2 commit files
     * @param com3 commit files
     * @param com4 commit files
     * @return commit id
     */
    private static String maxdepth(File com1, File com2, File com3, File com4) {
        Commit c1commit = Utils.readObject(com1, Commit.class);
        Commit c2commit = Utils.readObject(com2, Commit.class);
        Commit c3commit = Utils.readObject(com3, Commit.class);
        Commit c4commit = Utils.readObject(com4, Commit.class);
        Commit[] s = new Commit[]{c1commit, c2commit, c3commit, c4commit};
        int max = -1;
        int idx = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i].getlevel() > max) {
                idx = i;
                max = s[i].getlevel();
            }
        }
        return s[idx].getid();
    }

    /** Commit method for after merging.
     * @param branchname string
     * @param message  string
     * @param cbcom str
     * @param gbcom str*/
    private static void mergecommit(String message, String branchname,
                                    Commit cbcom, Commit gbcom) {
        try {
            Commit com1 = new Commit(message, _repo.getcommit(),
                    _repo.getfiles(), _repo.getbranch(),
                    _repo.getheadofbranch(branchname), cbcom.getlevel() + 1);
            Commit com2 = new Commit(message, _repo.getheadofbranch(branchname),
                    _repo.getfiles(), _repo.getbranch(),
                    _repo.getcommit(), gbcom.getlevel() + 1);
            _repo.addcommit(com1.getid());
            _repo.addcommit(com2.getid());
            _repo.sethead(com1.getid());
            _repo.setcommit(com1.getid());
            _repo.updatebranch(com1);
            _repo.updatebranch(branchname, com2.getid());
            File comfile = new File(".gitlet/commits/" + com1.getid());
            File comfile2 = new File(".gitlet/commits/" + com2.getid());
            Utils.writeObject(comfile, com1);
            Utils.writeObject(comfile2, com2);
            File stage = new File(".gitlet/staging");
            deletedir(stage);
            stage.mkdir();
            _repo.clearstage();
            _repo.clearremovedfiles();
            writerepo();
        } catch (IOException e) {
            Utils.message("Unexpected err");
        }
    }


    /** .gitlet folder.*/
    private static File _gitlet;

    /** Repository object representing the whole repo.*/
    private static Repo _repo;

}
