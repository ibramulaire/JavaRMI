
#include <thread>
#include <iostream>
#include <vector>
#include <sys/types.h>
#include <unistd.h>

int main( int argc, char *argv[] )   {
  
  // Create 10 threads
  std::vector<std::thread> threads;
  for (int i = 0; i < std::stoi(argv[1]); i++) {
    threads.push_back(std::thread([&]() {
 
      int statusb =  system("java Client 10 6");
    }));
  }

  // Join the threads to wait for them to finish
  for (auto& thread : threads) {
    thread.join();
  }

  std::cout << "All threads finished " << std::endl;

  return 0;
}