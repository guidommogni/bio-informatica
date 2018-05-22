package src;

import java.util.LinkedList;
import java.util.List;
import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.core.alignment.template.AlignedSequence;
import org.biojava.nbio.core.alignment.template.Profile;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AmbiguityDNACompoundSet;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.util.ConcurrencyTools;
import org.biojava.nbio.structure.align.StructurePairAligner;

public class Eje3 {

    public static void eje3() throws CompoundNotFoundException {
        ProteinSequence proteinSequence = new ProteinSequence("MILNKALLLGALALTTVMSPCGGEDIVAD" +
            "HVASCGVNLYQFYGPSGQYTHEFDGDEQFYVDLERKETAWRWPEFSKFGGFDPQGALRNMAVAKHNLNIMIKRYNSTAATNEVPEVTVFSK" +
            "SPVTLGQPNTLICLVDNIFPPVVNITWLSNGQSVTEGVSETSFLSKSDHSFFKISYLTFLPSADEIYDCKVEHWGLDQPLLKHWEPEIPA" +
            "PMSELTETVVCALGLSVGLMGIVVGTVFIIQGLRSVGASRHQGPL");

        ProteinSequence proteinSequence1 = new ProteinSequence("ATNEVPEVTVFSKSPVTLGQPNTLICLVDNI" +
            "FPPVVNITWLSNGQSVTEGVSETSFLSKSDHSFFKISYLTFLPSADEIYDCKVEHWGLDQPLLKHWEPEIL");

        ProteinSequence proteinSequence2 = new ProteinSequence("EDIVADHVASCGVNLYQFYGPSGQYTHEFDG" +
            "DEQFYVDLERKETAWRWPEFSKFGGFDPQGALRNMAVAKHNLNIMIKRYNSTAATNEVPEVTVFSKSPVTLGQPNTLICLVDNIFPPVVNIT" +
            "WLSNGQSVTEGVSETSFLSKSDHSFFKISYLTFLPSADEIYDCKVEHWGLDQPLLKHWEPEIPAPMSELTETVVCALGLSVGLMGIVVGTV" +
            "FIIQGLRSVGASRHQGPL");

        ProteinSequence proteinSequence3 = new ProteinSequence("LLGALALTTVMSPCGGEDIVADHVASCGVNLY" +
            "QFYGPSGQYTHEFDGDEQFYVDLERKETAWRWPEFSKFGGFDPQGALRNMAVAKHNLNIMIKRYNSTAATNEVPEVTVFSKSPVTLGQPNTLICL" +
            "VDNIFPPVVNITWLSNGQSVTEGVSETSFLSKSDHSFFKISYLTFLPSADEIYDCKVEHWGLDQPLLKHWEPEIPAPMSELTETVVCALGLSV" +
            "GLMGIVVGTVFIIQGLRSVGASRHQGPL");

        List<ProteinSequence> proteinSequenceList = new LinkedList<ProteinSequence>();
        proteinSequenceList.add(proteinSequence);
        proteinSequenceList.add(proteinSequence1);
        proteinSequenceList.add(proteinSequence2);
        proteinSequenceList.add(proteinSequence3);

        Profile<ProteinSequence, AminoAcidCompound> profile = Alignments.getMultipleSequenceAlignment(proteinSequenceList);
        for (AlignedSequence z :profile.getAlignedSequences()) {
            System.out.println(z);
        }
        //System.out.printf("Clustalw:%n%s%n", profile);
        ConcurrencyTools.shutdown();

    }
}
