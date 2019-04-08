import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[])
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		while (true)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Input filename");
			String fn = sc.nextLine();
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(fn));
				String temp;
				while ((temp = br.readLine()) != null)
				{
					String t[] = temp.split(" ");
					for (String s : t)
					{
					//	String ts = s;
						while (true)
						{
							if (s.length() == 0)
							{
								break;
							}
							else
							{
								if (s.charAt(0) < 'A' || (s.charAt(0) > 'Z' && s.charAt(0) < 'a') || s.charAt(0) > 'z')
								{
									s = s.substring(1);
								}
								else
								{
									break;
								}
							}
						}
						if (s.length() > 0)
						{
							if (map.containsKey(s))
							{
								map.put(s, map.get(s) + 1);
							}
							else
							{
								map.put(s, 1);
							}
						}
					}
				}
				br.close();
			}
			catch (IOException e)
			{
				System.out.println("Could not find");
				continue;
			}
			while (true)
			{
				System.out.println("To do (W-Stats by words  C-Count ranking  E-Export stats  Q-Quit)");
				String td = sc.nextLine();
				if (td.equals("W"))
				{
					System.out.println("Input word(s)");
					String temp;
					temp = sc.nextLine();
					String t[] = temp.split(" ");
					ArrayList<Integer> F = new ArrayList<Integer>();
					int sum = 0;
					for (String s : t)
					{
						System.out.print(s + ":" + String.valueOf(map.get(s)) + "\n");
						F.add(map.get(s));
						sum += map.get(s);
					}
					System.out.println();
					for (int i = 0; i < F.size(); i++)
					{
						F.set(i, 50 * F.get(i) / sum);
					}
					for (int i = 50; i >= 0; i--)
					{
						for (int j = 0; j < t.length; j++)
						{
							if (F.get(j) >= i)
							{
								System.out.print("        *");
							}
							else
							{
								System.out.print("         ");
							}
						}
						System.out.println();
					}
					for (int i = 0; i < t.length; i++)
					{
						System.out.printf("%9s", t[i]);
					}
					System.out.println();
				}
				else if (td.equals("C"))
				{
					int b;
					while (true)
					{
						System.out.println("Input number");
						b = sc.nextInt();
						sc.nextLine();
						if (b <= map.size())
						{
							break;
						}
						else
						{
							System.out.println("Too large");
						}
					}
					Set<String> temp = new HashSet<String>();
					for (int i = 0; i < b; i++)
					{
						int max = -1;
						String mk = null;
						for (String k : map.keySet())
						{
							if (map.get(k) > max && !temp.contains(k))
							{
								mk = k;
								max = map.get(k);
							}
						}
						System.out.print(mk + ":" + map.get(mk) + "\n");
						temp.add(mk);
					}
				}
				else if (td.equals("E"))
				{
					try
					{
						BufferedWriter bw = new BufferedWriter(new FileWriter("result.txt"));
						String[] w = new String[map.size()];
						int c = 0;
						for (String s : map.keySet())
						{
							w[c] = s;
							c++;
						}
						Arrays.sort(w);
						for (int i = 0; i < w.length; i++)
						{
							bw.write(w[i] + ":" + String.valueOf(map.get(w[i])) + "\r\n");
						}
						bw.flush();
						bw.close();
					}
					catch (IOException e)
					{
						
					}
				}
				else if (td.equals("Q"))
				{
					break;
				}
				else
				{
					System.out.println("Wrong command");
				}
			}
		}
	}
}
