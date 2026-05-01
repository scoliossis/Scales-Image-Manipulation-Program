cd ../

pandoc README.md -o convert/file.pdf \
  --toc \
  --toc-depth=3 \
  --pdf-engine=weasyprint \
  --highlight-style=tango \
  --css convert/hide-captions.css \
  --variable monofont="Courier New"