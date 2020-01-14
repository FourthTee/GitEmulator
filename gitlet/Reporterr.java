package gitlet;


/** Report specific errors to the gitlet system.
 * @author Fourth Teerakapibal
 */
public class Reporterr {

    /** Reports error when .gitlet folder already exists during init.*/
    static void gitexists() {
        Utils.message("A Gitlet version-control "
                + "system already exists in the current directory.");
        System.exit(0);
    }

    /** Report error when incorrect number
     * of operands are passed into the command.*/
    static void incorrectoper() {
        Utils.message("Incorrect operands.");
        System.exit(0);
    }

    /** Report error when no command is entered into gitlet. */
    static void nocommand() {
        Utils.message("Please enter a command.");
        System.exit(0);
    }

    /** Report Unexpected Error (for debugging purposes). */
    static void unexpectederr() {
        Utils.message("Unexpected Error.");
        System.exit(0);
    }

    /** Report error when running command while not in a gitlet directory.*/
    static void notindir() {
        Utils.message("Not in an initialized Gitlet directory.");
        System.exit(0);
    }

    /** Report error when command does not exist.*/
    static void commandnotexist() {
        Utils.message("No command with that name exists.");
        System.exit(0);
    }

    /** Report error when adding files that don't exist.*/
    static void filenotexist() {
        Utils.message("File does not exists.");
        System.exit(0);
    }

    /** Report error commit not found for find command.*/
    static void commitnotfound() {
        Utils.message("Found no commit with that message.");
        System.exit(0);
    }

    /** Report error no reason to remove. */
    static void noremove() {
        Utils.message("No reason to remove the file.");
        System.exit(0);
    }

    /** Report error no file in the commit (checkout).*/
    static void nofileincommit() {
        Utils.message("File does not exist in that commit.");
        System.exit(0);
    }

    /** Report error commit id does not exist.*/
    static void nocommitid() {
        Utils.message("No commit with that id exists.");
        System.exit(0);
    }

    /** Report error when a branch with that name already exist.*/
    static void branchalreadyexist() {
        Utils.message("branch with that name already exists.");
        System.exit(0);
    }

    /** Report error when branch does not exist (checkout).*/
    static void branchdne() {
        Utils.message("No such branch exists.");
        System.exit(0);
    }

    /** Report error when you are already on
     * the branch you are checking out to.*/
    static void alreadyonbranch() {
        Utils.message("No need to checkout the current branch.");
        System.exit(0);
    }

    /** Report error when branch does not exist (rm-branch).*/
    static void branchdne2() {
        Utils.message("A branch with that name does not exist.");
        System.exit(0);
    }

    /** Report error when you are already on the
     * branch you are checking out to. (rm-branch)*/
    static void alreadyonbranch2() {
        Utils.message("Cannot remove the current branch.");
        System.exit(0);
    }

    /** No files in staging area during commit.*/
    static void nofilestocommit() {
        Utils.message("No changes added to the commit.");
        System.exit(0);
    }

    /** Report err cannot merge with urself.*/
    static void selfmerge() {
        Utils.message("Cannot merge a branch with itself.");
        System.exit(0);
    }

    /** Report err need to delete or add file.*/
    static void untrack() {
        Utils.message("There is an untracked file in the way;"
                + " delete it or add it first.");
        System.exit(0);
    }

    /** Report err check staging and removal before merge.*/
    static void uncommitedchanges() {
        Utils.message("You have uncommitted changes.");
        System.exit(0);
    }











}
