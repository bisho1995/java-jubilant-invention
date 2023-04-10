package org.example.files;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

@Slf4j
public class FilePermission1 {
    public static void main(String[] args) {
        listFiles("/tmp");
    }

    @SneakyThrows
    private static void listFiles(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (!file.isDirectory()) {
                log.info("file => {}", file.getAbsolutePath());
                Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(file.toPath());
                log.info("permissions => {}", permissions);
                UserPrincipal owner = Files.getOwner(file.toPath());
                PosixFileAttributes attributes = Files.readAttributes(file.toPath(), PosixFileAttributes.class);
                GroupPrincipal group = attributes.group();
                log.info("owner => {}", owner);
                log.info("group => {}", group);
                String currentUser = System.getProperty("user.name");
                log.info("currentUser => {}", currentUser);
//                UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
//                String ownerName = lookupService.lookupPrincipalByName(owner.getName()).getName();
            } // end of if
        } // end of for
    }
}
