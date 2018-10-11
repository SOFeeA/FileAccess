package main;

import access.iFileSystem;
import access.iSecurity;

public class Application { 
	protected iSecurity mSecurity = null; 
	protected iFileSystem mFileSystem = null; 
	public String read_from_filesystem(String iPath) throws Exception {
		if (!mSecurity.isAuthorized("me", iPath)){
			throw new Exception("XYZ");
		} 
		return mFileSystem.getFile(iPath);
	} 

}
