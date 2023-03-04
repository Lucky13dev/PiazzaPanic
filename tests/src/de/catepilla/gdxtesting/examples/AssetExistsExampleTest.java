/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package de.catepilla.gdxtesting.examples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import de.catepilla.gdxtesting.GdxTestRunner;

import Sprites.Chef;

@RunWith(GdxTestRunner.class)
public class AssetExistsExampleTest {

	@Test
	public void ChefHoldingBunsFileExists() {
		assertTrue("This test will only pass when the Chef_holding_buns.jpg file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_buns.png").exists());
	}
}
