package com.tmtravlr.cp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.HashBiMap;


/*
 * This class was created to easly be able to shift through portals
 * and create automaticly the number of portals
 */
public class PortalsMaxLib {

	public List<Integer> Woolist = new ArrayList<Integer>();
	public List<Integer> Glasslist = new ArrayList<Integer>();
	public List<Integer> Panelist = new ArrayList<Integer>();
	public List<Integer> Claylist = new ArrayList<Integer>();
	
	/*public BiMap<Integer, CPPos> woolmap = HashBiMap.create();//new BiMap<Integer, CPPos>();
	public BiMap<Integer, CPPos> glassmap = HashBiMap.create();//new BiMap<Integer, CPPos>();
	public BiMap<Integer, CPPos> panemap = HashBiMap.create();//new BiMap<Integer, CPPos>();
	public BiMap<Integer, CPPos> claymap = HashBiMap.create();//new BiMap<Integer, CPPos>();*/
	
	public static LinkedList<CPPos> woolmap = new LinkedList();
	public static LinkedList<CPPos> glassmap = new LinkedList();
	public static LinkedList<CPPos> panemap = new LinkedList();
	public static LinkedList<CPPos> claymap = new LinkedList();
	
	public PortalsMaxLib(){
//max size for the moment is 256 portals of the same block,16 portals per metadata
	}
	
	
}
