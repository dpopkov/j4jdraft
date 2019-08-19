package ru.j4jdraft.sqlite;

import ru.j4jdraft.tools.CmdInput;
import ru.j4jdraft.tools.Resources;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class MainSave {
    public static void main(String[] args) throws SQLException, JAXBException, IOException, TransformerException {
        int size;
        if (args.length == 1) {
            size = Integer.parseInt(args[0]);
        } else {
            size = CmdInput.nextInt("Enter size: ");
        }
        Config config = new Config();
        config.init();
        StoreSQL store = new StoreSQL(config);
        store.generate(size);
        List<Entry> list = store.load();
        String filename = "tmp_sqlite.xml";
        Path source = Paths.get(filename);
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(list);
        System.out.println("Saved to " + filename);
        ConvertXSLT converter = new ConvertXSLT();
        Path xslt = Resources.getPath("xml/ConvertXslt.transform.xml");
        Path target = source.resolveSibling("new.xml");
        converter.convert(source, target, xslt);
        System.out.println("Written to " + target.toAbsolutePath());
    }
}
