package org.example;


import com.google.api.gax.paging.Page;
import com.google.cloud.bigquery.*;
import com.google.cloud.storage.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static final String BUCKET_NAME = "dataset-dumps";
    private static final String DATASET_ID="exploratory_datasets";
    private static final String PROJECT_ID = "ivory-voyage-362706";
    private static Storage storage;
    private static BigQuery bigQuery;
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
        bigQuery = BigQueryOptions.getDefaultInstance().getService();
//        uploadDataset("/Users/bisvarup.mukherjee/Downloads/airlinedelaycauses_DelayedFlights.parquet", "irlinedelaycauses_DelayedFlights");
    }
    private static void listDatasets(){
        Page<Dataset> datasetPage = bigQuery.listDatasets(PROJECT_ID);
        for(Dataset dataset: datasetPage.iterateAll()) {
            System.out.println(dataset.getDatasetId());
        }
    }
    private static void listBuckets(){
        System.out.println("Buckets");
        Page<Bucket> buckets = storage.list();
        for(Bucket bucket:buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }
        System.out.println("Listed all buckets");
    }

    private static void uploadDataset(final String filePath, final String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

        System.out.println(
                "File " + filePath + " uploaded to bucket " + BUCKET_NAME + " as " + fileName);

    }
}