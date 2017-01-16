package org.pinky83.alfaplus.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;

/**
 * Created by Дмитрий on 09.01.2017.
 *
 */
public class CollectionSizeFetcher {

        private static ByteArrayOutputStream baos;
        private static ObjectOutputStream out;

        public static long getCollectionSize(Collection o) throws IOException{
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            out.writeObject(o);
            out.close();
            return baos.toByteArray().length;
        }
}
