package gitlet;



/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author Fourth Teerakapibal
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) {
        try {
            if (args.length == 0) {
                Reporterr.nocommand();
            }
            String[] command = args;
            Command.process(command);

        } catch (IndexOutOfBoundsException e) {
            Reporterr.unexpectederr();
        }
    }

}
