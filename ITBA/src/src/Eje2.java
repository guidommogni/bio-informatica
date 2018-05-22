package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.biojava.nbio.core.sequence.io.util.IOUtils;
import org.biojava.nbio.ws.alignment.qblast.BlastOutputParameterEnum;
import org.biojava.nbio.ws.alignment.qblast.BlastProgramEnum;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastAlignmentProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastOutputProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastService;

public class Eje2 {

    public static void eje2() throws Exception {

        String HLA_DQA1 = "MILNKALLLGALALTTVMSPCGGEDIVADHVASCGVNLYQFYGPSGQYTH" +
            "EFDGDEQFYVDLERKETAWRWPEFSKFGGFDPQGALRNMAVAKHNLNIMI" +
            "KRYNSTAATNEVPEVTVFSKSPVTLGQPNTLICLVDNIFPPVVNITWLSN" +
            "GQSVTEGVSETSFLSKSDHSFFKISYLTFLPSADEIYDCKVEHWGLDQPL" +
            "LKHWEPEIPAPMSELTETVVCALGLSVGLMGIVVGTVFIIQGLRSVGASR" +
            "HQGPL";

        String HLA_DQB1 = "MSWKKALRIPGDLRVATVTLMLAMLSSLLAEGRDSPEDFVFQFKGMCYFT" +
            "NGTERVRLVTRYIYNREEYARFDSDVGVYRAVTPQGRPDAEYWNSQKEVL" +
            "EGTRAELDTVCRHNYEVAFRGILQRRVEPTVTISPSRTEALNHHNLLVCS" +
            "VTDFYPGQIKVRWFRNDQEETAGVVSTPLIRNGDWTFQILVMLEMTPQRG" +
            "DVYTCHVEHPSLQSPITVEWRAQSESAQSKMLSGVGGFVLGLIFLGLGLI" +
            "IRQRSQKGPQGPPPAGLLHL";


        final NCBIQBlastService ncbiqBlastService = new NCBIQBlastService();
        NCBIQBlastAlignmentProperties props = new NCBIQBlastAlignmentProperties();
        props.setBlastProgram(BlastProgramEnum.blastp);
        props.setBlastDatabase("swissprot");
        NCBIQBlastOutputProperties outputProps = new NCBIQBlastOutputProperties();
        outputProps.setOutputOption(BlastOutputParameterEnum.ALIGNMENTS,"200");
        String rid = ncbiqBlastService.sendAlignmentRequest(HLA_DQA1, props);

        // wait until results become available. Alternatively, one can do other computations/send other alignment requests
        while (!ncbiqBlastService.isReady(rid)) {
            System.out.println("Waiting for results. Sleeping for 5 seconds");
            Thread.sleep(5000);
        }

        final File f = new File("blastOut.txt");
        final InputStream in = ncbiqBlastService.getAlignmentResults(rid, outputProps);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        final FileWriter writer = new FileWriter(f);
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line + System.getProperty("line.separator"));
        }


        IOUtils.close(writer);
        IOUtils.close(reader);
        // delete given alignment results from blast server (optional operation)
        ncbiqBlastService.sendDeleteRequest(rid);

    }
}
