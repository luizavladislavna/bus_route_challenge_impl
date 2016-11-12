package com.go.euro.challenge.util;

import com.go.euro.challenge.entity.exception.ApplicationDefaultException;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
@Slf4j
public class FilesUtil {
	public static Runnable asUncheckedRunnable (Closeable c) {
		return () -> {
			try {
				c.close();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		};
	}

	public static void bufferedRead (BufferedReader br, Consumer<BufferedReader> function) {
		try {
			function.accept(br);
		} catch (RuntimeException e) {
			try {
				br.close();
			} catch (IOException ex) {
				try {
					e.addSuppressed(ex);
				} catch (Throwable ignore) {}
			}
			throw new ApplicationDefaultException(e.getMessage(), e);
		}
	}
}
