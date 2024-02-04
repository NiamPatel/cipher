public char[] blockReverse(int blockSize) {
    // Copy array
    char[] originalArray = this.original.toCharArray();
    char[] modified = new char[originalArray.length];

    for (int i = 0; i < Math.ceil(originalArray.length / blockSize); i++) {
        // Calculate the start and end indices for the current block
        int start = i * blockSize;
        int end = Math.min((i + 1) * blockSize, originalArray.length) - 1;

        // Reverse the characters in the current block
        for (int j = start; j <= end; j++) {
            modified[j] = originalArray[end - (j - start)];
        }
    }

    return modified;
}
