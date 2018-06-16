package src;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.io.FastaWriterHelper;
import org.biojava.nbio.core.sequence.io.GenbankReaderHelper;
import org.biojava.nbio.core.sequence.transcription.Frame;

public class Eje1 {

    public static void run (String fileName) throws Exception {
        final LinkedHashMap<String, DNASequence> genbank = GenbankReaderHelper.readGenbankDNASequence(new File(fileName + ".gb"));
        for (String x : genbank.keySet()) {
            DNASequence dnaSequence = genbank.get(x);
            int index = 0;
            for (Frame f : Frame.getAllFrames()) {
                final Set<ProteinSequence> proteinSequenceSet = new HashSet<ProteinSequence>();
                proteinSequenceSet.add(dnaSequence.getRNASequence(f).getProteinSequence());
                FastaWriterHelper.writeProteinSequence(new File(fileName + "_" + index++ +  ".fasta"), proteinSequenceSet);
            }
        }
    }

    public static void eje1Aux() throws Exception {
        final LinkedHashMap<String, DNASequence> genbank = GenbankReaderHelper.readGenbankDNASequence(new File("/Users/gmogni/ITBA/src/src/hda.gb"));
        FastaWriterHelper.writeNucleotideSequence(new File("/Users/gmogni/ITBA/src/src/out1.fa"), genbank.values());
    }
}

