/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfRect;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author virajee
 */
public class ComparingAlgorithm {

    public static int highestPercentageID = 0;

    static {
        ToolClassInSeparateJarInSharedDirectory.loadNativeLibrary();
    }

    public ComparingAlgorithm() {
    }

    //Reading image to the Mat 
    public Mat readImage(String imagePath) {
        //Mat--->n-dimensional level array class
        //imread()--->Loads an image from a file
        //imagePath-->Name of file to be loaded
        //Highgui.CV_LOAD_IMAGE_COLOR-->this parametr spcifies the color type of a loaded image.
        //CV_LOAD_IMAGE_COLOR-->convert image to the color one
        Mat imageMat = Highgui.imread(imagePath, Highgui.CV_LOAD_IMAGE_COLOR);
        return imageMat;
    }

    //detect keypoints of the image
    public MatOfKeyPoint detectKeyPoint(Mat matImage) {
        MatOfKeyPoint imageKeyPoints = new MatOfKeyPoint();
        //create()-->Creates a feature detector by its name
        FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SIFT);
        //FeatureDetector::detect---->Detects keypoints in an image
        //matImage-->Image    imageKeyPoints-->The detected keypoints
        featureDetector.detect(matImage, imageKeyPoints);
        return imageKeyPoints;
    }

    //compute descriptors
    public MatOfKeyPoint computeDescriptors(Mat matImage, MatOfKeyPoint imageKeyPoints) {
        MatOfKeyPoint imageDescriptors = new MatOfKeyPoint();
        //create()-->Creates a descriptor extractor by name
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);
        //DescriptorExtractor::compute--->Computes the descriptors for a set of keypoints detected in an image
        //matImage-->Image  imageKeyPoints--->Input collection of keypoints   imageDescriptors--->Computed descriptors   
        descriptorExtractor.compute(matImage, imageKeyPoints, imageDescriptors);
        return imageDescriptors;
    }

    //compare keypoints of two images
    public LinkedList<DMatch> compareTwoImages(MatOfKeyPoint imageDescriptor1, MatOfKeyPoint imageDescriptor2) {
        List matches = new LinkedList();
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
        //knnMatch()--->Finds the k best matches for each descriptor from a query set
        //In here k=2
        //imageDescriptor1--->Query set of descriptors
        //imageDescriptor2--->Train set of descriptors
        //matches--->Matches. Each matches[i] is k or less matches for the same query descriptor
        //Count of best matches found per each query descriptor or less if a query 
        //descriptor has less than k possible matches in total
        //Note: Nearest Neighbor matching is used in here
        descriptorMatcher.knnMatch(imageDescriptor1, imageDescriptor2, matches, 2);

        //calculate good match list
        /*After matching,invalid results should be discarded, 
         basically good matches should be filtered out. 
         To do that Nearest Neighbor Distance Ratio is used.
         */
        //Class DMatch-->Structure for matching:query descriptor index, train descriptor index,
        //train image index and distance between descriptors
        LinkedList<DMatch> goodMatchesList = new LinkedList<>();

        float nndrRatio = 0.7f;

        for (int i = 0; i < matches.size(); i++) {
            //Class MatOfDMatch-->A matrix whose element is cv::DMatch 
            MatOfDMatch matofDMatch = (MatOfDMatch) matches.get(i);
            DMatch[] dmatcharray = matofDMatch.toArray();
            DMatch m1 = dmatcharray[0];
            DMatch m2 = dmatcharray[1];

            if (m1.distance <= m2.distance * nndrRatio) {
                goodMatchesList.addLast(m1);
            }
        }
        return goodMatchesList;
    }

    //this method is for retrieving the matching percentage between manipulated and original image
    public double getPercentage(MatOfKeyPoint manipulatedImageMatOfKeyPoint, LinkedList<DMatch> goodMatchList) {
        double percentage;
        List<KeyPoint> imageKeyPoints = manipulatedImageMatOfKeyPoint.toList();
        System.out.println(goodMatchList.size() + " " + imageKeyPoints.size());
        percentage = ((double) goodMatchList.size() / (double) imageKeyPoints.size()) * 100;
        DecimalFormat format = new DecimalFormat("#.00");
        double new_percentage = Double.parseDouble(format.format(percentage));
        return new_percentage;
    }

    //this method is for get first four highest percentages
    public double[] getHighestPercentage(Map<Integer, Double> percentages) {
        double a[] = new double[8];
        double highestPercentage = 0.00;
        Map<Integer, Double> map = sortByValues((HashMap) percentages);
        int count = 0;//this variable is for counting the key-value pair
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator2.next();
            count++;
            if (count == map.size()) {
                highestPercentage = (double) me2.getValue();
                highestPercentageID = (int) me2.getKey();
                a[0] = highestPercentage;
                a[1] = highestPercentageID;
            }
            if (count == map.size() - 1) {
                highestPercentage = (double) me2.getValue();
                highestPercentageID = (int) me2.getKey();
                a[2] = highestPercentage;
                a[3] = highestPercentageID;
            }
            if (count == map.size() - 2) {
                highestPercentage = (double) me2.getValue();
                highestPercentageID = (int) me2.getKey();
                a[4] = highestPercentage;
                a[5] = highestPercentageID;
            }
            if (count == map.size() - 3) {
                highestPercentage = (double) me2.getValue();
                highestPercentageID = (int) me2.getKey();
                a[6] = highestPercentage;
                a[7] = highestPercentageID;
            }
        }
        return a;
    }

    //this method is for get the highest percentage
    public double[] getHighestPercentage2(Map<Integer, Double> percentages) {
        double a[] = new double[2];
        double highestPercentage = 0.00;
        Map<Integer, Double> map = sortByValues((HashMap) percentages);
        int count = 0;//this variable is for counting the key-value pair
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator2.next();
            count++;
            if (count == map.size() - 1) {
                highestPercentage = (double) me2.getValue();
                highestPercentageID = (int) me2.getKey();
                a[0] = highestPercentage;
                a[1] = highestPercentageID;
            }
        }
        return a;
    }

    //this method is for sorting the HashMap
    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public int detectFace(String path) {
        int i = 0;
        //create an instance of CascadeClassifier passing it the name of the file from which the classifier is loaded
        CascadeClassifier faceDetector = new CascadeClassifier(ComparingAlgorithm.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));

        //convert the image to a format which the Java API will accept using the Highui class. Mat is the OpenCV C++ n-dimensional dense array class.
        System.out.println(path);
        Mat image = Highgui.imread(path);

        MatOfRect faceDetections = new MatOfRect();

        //call the detectMultiScale method on the classifier passing it the image and MatOfRect object.
        //After processing,the MatOfRect will have face detections.
        faceDetector.detectMultiScale(image, faceDetections);

        i = faceDetections.toArray().length;
        return i;

    }
}
