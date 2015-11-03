package nju.iip.BloomFilter;

import java.io.File;

import nju.iip.util.Config;


public class BloomFactory {
	private static BloomFilter<String> bf; 
	private static String synth = "";
	private static BloomFactory bloomFactory; 
	private String path;

	private BloomFactory() {
		path = System.getProperty("user.dir") + "/bloom/bloomfilter.bloom";
		File file = new File(path);
		if (file.exists()) {
			bf = new BloomFilter<String>(file);
		} else {
			bf = new BloomFilter<String>(1, Integer.parseInt(Config.getValue("bloomSize")));
		}
	}

	public static BloomFactory getInstance() {
			synchronized (synth) {
				if (bloomFactory == null) {
					bloomFactory = new BloomFactory();
				}
		    }
		return bloomFactory;
	}

	/**
	 * save BoolmFilter
	 * 
	 * @return
	 */
	public synchronized void saveBloomFilter() {
		synchronized (bf) {
			bf.save(path);
		}
	}

	/**
	 * 增加
	 * 
	 * @param url
	 * @param Class_type
	 * @param type
	 * @return
	 */
	public void add(String url) {
		synchronized (bf) {
			bf.add(url);
		}
	}

	/**
	 * 是否已经存在URL
	 * 
	 * @param url
	 * @param Class_type
	 * @param type
	 * @return
	 */
	public boolean contains(String url) {
		if (bf.contains(url)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		BloomFactory bf = BloomFactory.getInstance();
		bf.add("wq");
		bf.saveBloomFilter();
		System.out.println(bf.contains("w")+bf.path);
	}
	
}