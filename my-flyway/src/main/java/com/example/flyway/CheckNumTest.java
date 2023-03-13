package com.example.flyway;

import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.migration.JavaMigration;
import org.flywaydb.core.internal.resource.LoadableResource;
import org.flywaydb.core.internal.resource.ResourceProvider;
import org.flywaydb.core.internal.scanner.Scanner;
import org.flywaydb.core.internal.util.BomFilter;
import org.flywaydb.core.internal.util.IOUtils;
import org.flywaydb.core.internal.util.Locations;
import org.flywaydb.core.internal.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.zip.CRC32;


/**
 * 使用flyway计算脚本的checksum值
 */
public class CheckNumTest {
    
    public static void main(String[] args) {
        String prefix = "V";
        String[] suffixes = {".sql"};
        Locations locations = new Locations("db/migration");
        Location[] arr = (Location[]) locations.getLocations().toArray(new Location[0]);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Charset encoding = StandardCharsets.UTF_8;
        Scanner<JavaMigration> scanner = new Scanner(JavaMigration.class, Arrays.asList(arr), classLoader, encoding);
        Iterator<LoadableResource> resources = ((ResourceProvider) scanner).getResources(prefix, suffixes).iterator();
        while (resources.hasNext()) {
            LoadableResource resource = resources.next();
            // 原生方法
            System.out.println(String.format("%12s", resource.checksum()) + " " + resource.getFilename());
            // 第二种方法
//            System.out.println(String.format("%12s", calculateChecksum(resource)) + " " + resource.getFilename());
//            System.out.println(calculateChecksum(resource) + "      " + resource.getFilename());
        }
    }
    
    /**
     * <pre>
     * @description: SqlMigrationResolver.calculateChecksum
     * </pre>
     *
     * @throws
     * @author dongshanshan
     * @param: resource
     * @param: str
     * @return: int
     */
    static int calculateChecksum(LoadableResource resource) {
        CRC32 crc32 = new CRC32();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(resource.read(), 4096);
            String line = reader.readLine();
            if (line != null) {
                line = BomFilter.FilterBomFromString(line);
                do {
                    crc32.update(StringUtils.trimLineBreak(line).getBytes(StandardCharsets.UTF_8));
                } while ((line = reader.readLine()) != null);
            }
        } catch (IOException var7) {
            throw new FlywayException("Unable to calculate checksum for " + resource.getFilename() + " : " + var7.getMessage(), var7);
        } finally {
            IOUtils.close(reader);
        }
        
        return (int) crc32.getValue();
    }
}
