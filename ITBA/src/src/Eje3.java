package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.core.alignment.template.AlignedSequence;
import org.biojava.nbio.core.alignment.template.Profile;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.util.ConcurrencyTools;

public class Eje3 {

    public static void run (final String fileName, final int size) throws Exception {

        final List<ProteinSequence> proteinSequenceList = new LinkedList<ProteinSequence>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        Pattern proteinSequencePattern = Pattern.compile("(.*):(.*)");
        Matcher proteinMatcher;
        while ((line = br.readLine()) != null) {
            proteinMatcher = proteinSequencePattern.matcher(line);
            if (proteinMatcher.find()) {
                proteinSequenceList.add(new ProteinSequence(proteinMatcher.group(2)));
            }
        }

        final List<ProteinSequence> proteinSequencesFinalList;
        if (size < 1 || size > proteinSequenceList.size()) {
            proteinSequencesFinalList = proteinSequenceList;
        } else {
            proteinSequencesFinalList = proteinSequenceList.subList(0, size - 1);
        }
        Profile<ProteinSequence, AminoAcidCompound> profile = Alignments.getMultipleSequenceAlignment(proteinSequencesFinalList);
        for (AlignedSequence z :profile.getAlignedSequences()) {
            System.out.println(z);
        }
        //System.out.printf("Clustalw:%n%s%n", profile);
        ConcurrencyTools.shutdown();
        br.close();
    }
}
