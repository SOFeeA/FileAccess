package main;

import static org.mockito.Mockito.*;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import access.iFileSystem;
import access.iSecurity;

class UTApplication {

	private static final String cPathValid = "ABC";
	private static final String cFILE_TEXT = "FileText";
	private static final String cPathInvalid = "XZY";
	private Application mFcut;

	@BeforeEach
	void setUp() throws Exception {
		mFcut = new Application();
	}

	@Test
	final void callMethod() {
		try {
			mockSecurity("me");
			mockFileSystem();
			 
			mFcut.read_from_filesystem(cPathValid);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	final void ret_string() {
		try {
			mockSecurity("me");
			mockFileSystem();
			String lRet = mFcut.read_from_filesystem(cPathValid);
			assertNotNull(lRet);
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	final void ret_file() {
		try { 
			mockSecurity("me");
			mockFileSystem();
			
			String lRet = mFcut.read_from_filesystem(cPathValid);
			assertNotNull(lRet);
			assertEquals(cFILE_TEXT, lRet);
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	final void invPath_Exc() {
		try { 
			mockFileSystem();
			
			mFcut.read_from_filesystem(cPathInvalid);
			fail();
		} catch (Exception e) {
		}
	}
	@Test
	final void invUser_Exc() {
		try { 
			mockFileSystem();
			mockSecurity("You");
			
			mFcut.read_from_filesystem(cPathValid);
			fail();
		} catch (Exception e) {
		}
	}

	private void mockFileSystem() {
		mFcut.mFileSystem = mock(iFileSystem.class); 
		when(mFcut.mFileSystem.getFile(cPathValid)).thenReturn(cFILE_TEXT);
	}
	private void mockSecurity(String iValidUser) {
		mFcut.mSecurity = mock(iSecurity.class); 
		when(mFcut.mSecurity.isAuthorized(iValidUser, cPathValid)).thenReturn(true);	
	}
}
