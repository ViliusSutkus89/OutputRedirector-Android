/*
 * OutputRedirector.java
 * OutputRedirector-Android - Redirect stdout and stderr to file
 *
 * Copyright (C) 2020 Vilius Sutkus'89
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.viliussutkus89.android.outputredirector;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;


public final class OutputRedirector {
  static {
    System.loadLibrary("outputredirector");
  }

  public static void redirectToLogsFolderInCache(@NonNull Context ctx) {
    File logsDir = new File(ctx.getCacheDir(), "logs");
    redirectToFolder(logsDir);
  }

  public static void redirectToFolder(@NonNull File folder) {
    folder.mkdirs();

    File stdout = new File(folder, "stdout.log");
    File stderr = new File(folder, "stderr.log");

    redirect(stdout.getAbsolutePath(), stderr.getAbsolutePath());
  }

  public static native void redirect(@NonNull String stdout, @NonNull String stderr);
}
