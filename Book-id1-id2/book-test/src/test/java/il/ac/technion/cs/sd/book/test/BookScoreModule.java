package il.ac.technion.cs.sd.book.test;

import com.google.inject.AbstractModule;

import il.ac.technion.cs.sd.book.ext.LineStorage;

// This module is in the testing project, so that it could easily bind all dependencies from all levels.
class BookScoreModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(LineStorage.class).to(implementation)
  }
}
